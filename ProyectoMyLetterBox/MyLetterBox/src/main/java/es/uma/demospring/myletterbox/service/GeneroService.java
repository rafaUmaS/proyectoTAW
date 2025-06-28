package es.uma.demospring.myletterbox.service;

import es.uma.demospring.myletterbox.dao.GenreRepository;
import es.uma.demospring.myletterbox.dto.GeneroDTO;
import es.uma.demospring.myletterbox.entity.EntityGenre;
import es.uma.demospring.myletterbox.entity.EntityMovie;
import es.uma.demospring.myletterbox.entity.EntityProductionCountries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Autor(es): Álvaro Sierra García (80%)
 */

@Service
public class GeneroService extends DTOService<GeneroDTO, EntityGenre> {

    @Autowired private GenreRepository genreRepository;

    public List<GeneroDTO> listarGeneros (){
        List<EntityGenre> generos = this.genreRepository.findAll();
        return this.entity2DTO(generos);
    }

    public GeneroDTO buscarGeneroById(Integer id){
        EntityGenre genero = this.genreRepository.findById(id).orElse(null);
        if (genero != null) {
            return genero.toDTO();
        } else {
            return new GeneroDTO();
        }
    }

    public void guardarGenero(GeneroDTO generoDTO) {
        if(generoDTO.getId() == null){
            EntityGenre genero = new EntityGenre();
            genero.setName(generoDTO.getNombre());
            this.genreRepository.save(genero);
        } else {
            EntityGenre genero = this.genreRepository.findById(generoDTO.getId()).orElse(null);
            if(genero != null){
                genero.setName(generoDTO.getNombre());
                this.genreRepository.save(genero);
            }
        }
    }

    public void eliminarGeneroById(Integer id) {
        EntityGenre genero = this.genreRepository.findById(id).orElse(null);

        List<EntityMovie> movies = genero.getMovieList();
        if(genero!=null && !movies.isEmpty()) {
            for (EntityMovie movie : movies) {
                List<EntityGenre> generoDel = movie.getGenreList();
                generoDel.remove(genero);
                movie.setGenreList(generoDel);
            }
        }
        this.genreRepository.deleteById(id);
    }

    public List<GeneroDTO> entityGenreList2DTO (List<EntityGenre> generos){
        return this.entity2DTO(generos);
    }
}
