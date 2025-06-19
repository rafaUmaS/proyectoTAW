package es.uma.demospring.myletterbox.service;

import es.uma.demospring.myletterbox.dao.PersonaRepository;
import es.uma.demospring.myletterbox.entity.EntityMovie;
import es.uma.demospring.myletterbox.entity.EntityPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {

    @Autowired protected PersonaRepository personaRepository;


    public List<EntityPersona> getPersonasOrdenadas(String campo, boolean ascendente, String nombre) {
        Sort sort = Sort.by(ascendente ? Sort.Direction.ASC : Sort.Direction.DESC, campo);
        List<EntityPersona> personas;
        if(nombre==null || nombre.isEmpty()){
            personas = personaRepository.findAll(sort);
        } else {
            personas = this.personaRepository.findByNameContainingIgnoreCase(nombre, sort);
        }
        return personas;
    }

    public List<EntityPersona> listarPersonas(){
        return this.listarPersonas(null);
    }

    public List<EntityPersona> listarPersonas(String nombre){
        List<EntityPersona> personas;

        if (nombre==null || nombre.isEmpty()){
            personas = this.personaRepository.findAll();
        } else {
            personas = this.personaRepository.buscarPersonaPorFiltro(nombre);
        }
        return personas;
    }
}
