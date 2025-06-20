package es.uma.demospring.myletterbox.service;

import es.uma.demospring.myletterbox.dao.GenreRepository;
import es.uma.demospring.myletterbox.dao.MovieRepository;
import es.uma.demospring.myletterbox.dao.UsuarioRepository;
import es.uma.demospring.myletterbox.dto.MovieDTO;
import es.uma.demospring.myletterbox.dto.UsuarioSaveMovieDTO;
import es.uma.demospring.myletterbox.entity.EntityGenre;
import es.uma.demospring.myletterbox.entity.EntityMovie;
import es.uma.demospring.myletterbox.entity.EntityUsuarioSaveMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* Autor(es): Ivan Pedraza Díez (100%)
*/

@Service
public class MovieService extends DTOService<MovieDTO, EntityMovie> {

    @Autowired private MovieRepository movieRepository;

    @Autowired private GenreRepository genreRepository;

    public List<EntityMovie> getMoviesOrdenadas(String campo, boolean ascendente, String nombre) {
        Sort sort = Sort.by(ascendente ? Sort.Direction.ASC : Sort.Direction.DESC, campo);
        List<EntityMovie> movies;
        if(nombre==null || nombre.isEmpty()){
            movies = movieRepository.findAll(sort);
        } else {
            movies = this.movieRepository.findByNameContainingIgnoreCase(nombre, sort);
        }
        return movies;
    }

    public List<EntityMovie> listarMovies() {
        return this.listarMovies(null);
    }

    public List<EntityMovie> listarMovies(String nombre) {

        List<EntityMovie> movies;

        if (nombre==null || nombre.isEmpty()){
            movies = this.movieRepository.findAll();
        } else {
            movies = this.movieRepository.buscarMoviePorFiltro(nombre);
        }
        return movies;
    }

    public Map<Integer,MovieDTO> listarMoviesDTO() {
        List<EntityMovie> entidades = movieRepository.findAll();
        Map<Integer, MovieDTO> agrupado = new HashMap<>();

        for (EntityMovie e : entidades) {
            MovieDTO dto = new MovieDTO();
            dto.setMovieId(e.getMovieId());
            dto.setName(e.getName());
            dto.setVoteAverage(e.getVoteAverage());

            agrupado.put(e.getMovieId(), dto);
        }

        return agrupado;
    }

    public EntityMovie buscarMovieById(Integer id){
        return this.movieRepository.findById(id).orElse(null);
    }

    public MovieDTO getMovieDTOById(Integer id){
        EntityMovie movie = this.movieRepository.findById(id).orElse(null);

        if(movie == null){
            return null;
        } else {
            MovieDTO dto = new MovieDTO();

            dto.setMovieId(movie.getMovieId());
            dto.setName(movie.getTitle());
            dto.setOriginalTittle(movie.getOriginalTitle());
            dto.setLanguage(movie.getOriginalLanguage());
            dto.setDescription(movie.getOverview());
            dto.setPopularity(movie.getPopularity());
            dto.setRevenue(movie.getRevenue());
            dto.setBudget(movie.getBudget());
            dto.setDuration(movie.getRuntime());
            dto.setVoteNumber(movie.getVoteCount());
            dto.setVoteAverage(movie.getVoteAverage());
            dto.setDate(movie.getReleaseDate());
            dto.setEstado(movie.getStatus());
            dto.setGeneros(new ArrayList<>());

            if(movie.getGenreList() != null){
                for(EntityGenre g : movie.getGenreList()){
                    dto.getGeneros().add(g.getId());
                }
            }

            return dto;
        }
    }

    public void guardarMovie(MovieDTO dto) {
        EntityMovie movie;
        if(dto.getMovieId() == null){
            movie = new EntityMovie();
        } else {
            movie = movieRepository.findById(dto.getMovieId()).orElse(new EntityMovie());
        }

        movie.setName(dto.getName());
        movie.setTitle(dto.getName());
        movie.setOriginalTitle(dto.getOriginalTittle());
        movie.setOriginalLanguage(dto.getLanguage());
        movie.setOverview(dto.getDescription());
        movie.setPopularity(dto.getPopularity());
        movie.setRevenue(dto.getRevenue());
        movie.setBudget(dto.getBudget());
        movie.setRuntime(dto.getDuration());
        movie.setVoteCount(dto.getVoteNumber());
        movie.setVoteAverage(dto.getVoteAverage());
        movie.setReleaseDate(dto.getDate());
        movie.setStatus(dto.getEstado());

        if (dto.getGeneros() != null) {
            List<EntityGenre> generos = genreRepository.findAllById(dto.getGeneros());
            movie.setGenreList(generos);
        }

        movieRepository.save(movie);
    }

    public void borrarMovieById(Integer id) {
        this.movieRepository.deleteById(id);
    }
}
