package es.uma.demospring.myletterbox.service;

import es.uma.demospring.myletterbox.dao.MovieRepository;
import es.uma.demospring.myletterbox.dao.UsuarioSaveMovieRepository;
import es.uma.demospring.myletterbox.dto.UsuarioSaveMovieDTO;
import es.uma.demospring.myletterbox.entity.EntityMovie;
import es.uma.demospring.myletterbox.entity.EntityUsuario;
import es.uma.demospring.myletterbox.entity.EntityUsuarioSaveMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserMovieService extends DTOService<UsuarioSaveMovieDTO,EntityUsuarioSaveMovie> {
    @Autowired
    private UsuarioSaveMovieRepository usuarioSaveMovieRepository;
    @Autowired
    private MovieRepository movieRepository;

    public void recomendarPelicula(Integer movieId, String nombreLista, String nombreListaBase, String crearNueva, EntityUsuario usuario) {
        EntityMovie movie = movieRepository.findById(movieId).orElseThrow();

        String nombreFinalLista;

        if (crearNueva.equals("true")) {
            if (nombreListaBase == null || nombreListaBase.trim().isEmpty()) {
                throw new IllegalArgumentException("NombreDeListaBaseVacio");
            }
            nombreFinalLista = nombreListaBase.trim() + " Recomendado";
            boolean existe = usuarioSaveMovieRepository.findListaByNombreNormalizado(nombreFinalLista);
            if (existe) {
                throw new IllegalArgumentException("NombreDuplicado");
            }
        } else {
            nombreFinalLista = nombreLista;
        }

        boolean yaExiste = usuarioSaveMovieRepository.existsByUsuarioAndMovieAndName(usuario, movie, nombreFinalLista);
        if (yaExiste) {
            throw new IllegalArgumentException("LaRecomendacionYaExiste");
        }

        // Crear la entidad directamente
        EntityUsuarioSaveMovie saveMovie = new EntityUsuarioSaveMovie();
        saveMovie.setName(nombreFinalLista);
        saveMovie.setMovieMovieId(movie);
        saveMovie.setUsuarioUserId(usuario);

        usuarioSaveMovieRepository.save(saveMovie);
    }



    public void eliminarRecomendacion(Integer saveMovieId) {
        usuarioSaveMovieRepository.deleteById(saveMovieId);
    }

    public Map<String, List<UsuarioSaveMovieDTO>> obtenerPeliculasRecomendadas() {
        List<EntityUsuarioSaveMovie> entidades = usuarioSaveMovieRepository.findAllByNameContaining("Recomendado");
        Map<String, List<UsuarioSaveMovieDTO>> agrupado = new HashMap<>();

        for (EntityUsuarioSaveMovie e : entidades) {
            UsuarioSaveMovieDTO dto = new UsuarioSaveMovieDTO();
            dto.setId(e.getId());
            dto.setName(e.getName());
            dto.setMovieId(e.getMovieMovieId().getMovieId());
            dto.setUserId(e.getUsuarioUserId().getUserId());

            agrupado.computeIfAbsent(dto.getName(), k -> new ArrayList<>()).add(dto);
        }

        return agrupado;
    }
}

