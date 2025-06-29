package es.uma.demospring.myletterbox.service;

import es.uma.demospring.myletterbox.dao.CastRepository;
import es.uma.demospring.myletterbox.dao.CrewRepository;
import es.uma.demospring.myletterbox.dto.CastDTO;
import es.uma.demospring.myletterbox.entity.EntityCast;
import es.uma.demospring.myletterbox.entity.EntityCrew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Autor(es): Álvaro Sierra García (90%), Iván Pedraza Díez (10%)
 */

@Service
public class CastService extends DTOService<CastDTO, EntityCast> {

    @Autowired private CastRepository castRepository;

    @Autowired private CrewRepository crewRepository;

    public List<CastDTO> listarCasts() {
        List<EntityCast> casts = this.castRepository.findAll();
        return castEntityList2DTO(casts);
    }

    public CastDTO buscarCastById(Integer id) {
        EntityCast cast = this.castRepository.findById(id).orElse(null);
        if (cast != null) {
            return cast.toDTO();
        } else {
            return null;
        }
    }

    public void guardarCast(CastDTO castDTO) {
        if (castDTO.getId() == null) {
            EntityCast cast = new EntityCast();
            cast.setName(castDTO.getName());
            cast.setGender(castDTO.getGender());
            cast.setCharacter(castDTO.getCharacter());

            EntityCrew crew = this.crewRepository.findById(castDTO.getCrew()).orElse(null);
            cast.setCrewid(crew);
            if(crew != null) {
                this.castRepository.save(cast);
            }
        } else {
            EntityCast cast = this.castRepository.findById(castDTO.getId()).orElse(null);
            if(cast != null){
                cast.setName(castDTO.getName());
                cast.setGender(castDTO.getGender());
                cast.setCharacter(castDTO.getCharacter());

                EntityCrew crew = this.crewRepository.findById(castDTO.getCrew()).orElse(null);
                cast.setCrewid(crew);
                if(crew != null) {
                    this.castRepository.save(cast);
                }
            }
        }
    }

    public void eliminarCastById(Integer id) {
        this.castRepository.deleteById(id);
    }

    public List<CastDTO> castEntityList2DTO(List<EntityCast> castList){
        return this.entity2DTO(castList);
    }

}
