package es.uma.demospring.myletterbox.service;

import es.uma.demospring.myletterbox.dao.GenreRepository;
import es.uma.demospring.myletterbox.dto.GeneroDTO;
import es.uma.demospring.myletterbox.entity.EntityGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService extends DTOService<GeneroDTO, EntityGenre>  {

    @Autowired private GenreRepository genreRepository;

    public List<GeneroDTO> listarGeneros (){
        List<EntityGenre> generos = genreRepository.findAll();
        return this.entity2DTO(generos);
    }
}
