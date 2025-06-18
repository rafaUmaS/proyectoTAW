package es.uma.demospring.myletterbox.service;

import es.uma.demospring.myletterbox.dao.MovieRepository;
import es.uma.demospring.myletterbox.entity.EntityMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

/*
* Autor(es): Ivan Pedraza Díez (100%)
*/

@Service
public class MovieService {

@Autowired private MovieRepository movieRepository;

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

    public EntityMovie buscarMovieById(Integer id){
        return this.movieRepository.findById(id).orElse(null);
    }
}
