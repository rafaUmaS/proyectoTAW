package es.uma.demospring.myletterbox.service;

import es.uma.demospring.myletterbox.dao.MovieRepository;
import es.uma.demospring.myletterbox.entity.EntityMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieService {

@Autowired private MovieRepository movieRepository;

    public List<EntityMovie> getMoviesOrdenadas(String campo, boolean ascendente) {
        Sort sort = Sort.by(ascendente ? Sort.Direction.ASC : Sort.Direction.DESC, campo);
        return movieRepository.findAll(sort);
    }
}
