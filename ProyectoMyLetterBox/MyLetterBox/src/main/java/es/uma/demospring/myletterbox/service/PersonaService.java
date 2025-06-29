package es.uma.demospring.myletterbox.service;

import es.uma.demospring.myletterbox.dao.PersonaRepository;
import es.uma.demospring.myletterbox.dto.PersonaDTO;
import es.uma.demospring.myletterbox.entity.EntityCrew;
import es.uma.demospring.myletterbox.entity.EntityMovie;
import es.uma.demospring.myletterbox.entity.EntityPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

/*
 * Autor(es): Ivan Pedraza Díez (100%)
 */

@Service
public class PersonaService {

    @Autowired protected PersonaRepository personaRepository;


    public List<PersonaDTO> getPersonasOrdenadas(String campo, boolean ascendente, String nombre) {

        if (!campo.equals("num_peliculas")) {
            Sort sort = Sort.by(ascendente ? Sort.Direction.ASC : Sort.Direction.DESC, campo);
            List<EntityPersona> personas;
            if (nombre == null || nombre.isEmpty()) {
                personas = personaRepository.findAll(sort);
            } else {
                personas = this.personaRepository.findByNameContainingIgnoreCase(nombre, sort);
            }
            return this.listarPersonaDTO(personas);
        }else {
            if (nombre==null){
                return this.getPersonasConNumeroPeliculas(ascendente, "");
            }else {
                return this.getPersonasConNumeroPeliculas(ascendente, nombre);
            }
        }

    }

    public List<PersonaDTO> listarPersonas(){
        return this.listarPersonas(null);
    }

    public List<PersonaDTO> listarPersonas(String nombre){
        List<EntityPersona> personas;

        if (nombre==null || nombre.isEmpty()){
            personas = this.personaRepository.findAll();
        } else {
            personas = this.personaRepository.buscarPersonaPorFiltro(nombre);
        }
        return this.listarPersonaDTO(personas);
    }

    public List<PersonaDTO> getPersonasConNumeroPeliculas(boolean ascendente, String nombre) {
        return ascendente ?
                personaRepository.findPersonasConNumeroPeliculasAsc(nombre) :
                personaRepository.findPersonasConNumeroPeliculasDesc(nombre);
    }

    public PersonaDTO listarPersonaDTO(EntityPersona persona){
        List<EntityPersona> personaList = new ArrayList<>();
        personaList.add(persona);
        return this.listarPersonaDTO(personaList).get(0);
    }

    public List<PersonaDTO> listarPersonaDTO(List<EntityPersona> personas){
        List<PersonaDTO> personasDTO = new ArrayList<>();

        for(EntityPersona persona : personas){
            PersonaDTO auxDTO = new PersonaDTO(persona.getId(), persona.getName(), (long)persona.getCrewList().size());

            if(persona.getCrewList() != null){
                List<Integer> crewIdList = new ArrayList<>();
                for(EntityCrew crew : persona.getCrewList()){
                    crewIdList.add(crew.getId());
                }
                auxDTO.setCrewList(crewIdList);
            }

            personasDTO.add(auxDTO);
        }

        return personasDTO;
    }

    public PersonaDTO getPersonaById(Integer id){
        return this.listarPersonaDTO(this.personaRepository.findById(id).orElse(null));
    }

}
