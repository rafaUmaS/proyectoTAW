package es.uma.demospring.myletterbox.service;

import es.uma.demospring.myletterbox.dao.CastRepository;
import es.uma.demospring.myletterbox.dao.CrewRepository;
import es.uma.demospring.myletterbox.dao.MovieRepository;
import es.uma.demospring.myletterbox.dao.PersonaRepository;
import es.uma.demospring.myletterbox.dto.CastDTO;
import es.uma.demospring.myletterbox.dto.CrewDTO;
import es.uma.demospring.myletterbox.entity.EntityCast;
import es.uma.demospring.myletterbox.entity.EntityCrew;
import es.uma.demospring.myletterbox.entity.EntityMovie;
import es.uma.demospring.myletterbox.entity.EntityPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrewService extends DTOService<CrewDTO, EntityCrew>{

    @Autowired private CrewRepository crewRepository;

    @Autowired private CastService castService;

    @Autowired private PersonaRepository personaRepository;

    @Autowired private MovieRepository movieRepository;

    @Autowired private CastRepository castRepository;

    public List<CrewDTO> listarCrews(){
        List<EntityCrew> crews = crewRepository.findAll();
        return entity2DTO(crews);
    }

    public CrewDTO buscarCrewById(Integer id) {
        EntityCrew crew = this.crewRepository.findById(id).orElse(null);
        if (crew != null) {
            return crew.toDTO();
        } else {
            return null;
        }
    }

    public void guardarCrew(CrewDTO crewDTO) {
        EntityCrew crew;
        if (crewDTO.getId() == null) {
            crew = new EntityCrew();
        } else {
            crew = this.crewRepository.findById(crewDTO.getId()).orElse(new EntityCrew());
        }

        crew.setCrewRole(crewDTO.getCrewRole());

        EntityPersona p = this.personaRepository.findById(crewDTO.getPERSONAid()).orElse(null);
        if(p != null){
            crew.setPERSONAid(p);
        }

        EntityMovie m = this.movieRepository.findById(crewDTO.getMovieId()).orElse(null);
        if(m != null){
            crew.setMoviemovieid(m);
        }

        crew = this.crewRepository.save(crew);

        List<EntityCast> actualesCasts = this.castRepository.findByCrewid(crew);
        List<Integer> castIdsSeleccionados = crewDTO.getCastIds() != null ? crewDTO.getCastIds() : new ArrayList<>();
        List<EntityCast> castsSeleccionados = this.castRepository.findAllById(castIdsSeleccionados);

        for (EntityCast castActual : actualesCasts) {
            if (!castIdsSeleccionados.contains(castActual.getId())) {
                castActual.setCrewid(null);
                this.castRepository.save(castActual);
            }
        }

        for (EntityCast castSel : castsSeleccionados) {
            if (!crew.equals(castSel.getCrewid())) {
                castSel.setCrewid(crew);
                this.castRepository.save(castSel);
            }
        }

        crew.setCastList(castsSeleccionados);
        this.crewRepository.save(crew);
    }

    public void eliminarCrewById(Integer id) {
        this.crewRepository.deleteById(id);
    }

    public CrewDTO getCrewById(Integer crewId){
        List<Integer> crewIdList = new ArrayList<>();
        crewIdList.add(crewId);
        return this.listarCrewById(crewIdList).get(0);
    }

    public List<CrewDTO> listarCrewById(List<Integer> crewIdList){
        List<EntityCrew> listaCrew = this.crewRepository.findAllById(crewIdList);

        return this.entityCrewList2DTO(listaCrew);
    }

    public List<CrewDTO> entityCrewList2DTO(List<EntityCrew> crewList){

        List<CrewDTO> crewDTOList = new ArrayList<>();

        for (EntityCrew crew : crewList){
            CrewDTO dto = new CrewDTO();

            dto.setCrewRole(crew.getCrewRole());
            dto.setId(crew.getId());

            dto.setMovieId(crew.getMoviemovieid().getMovieId());



            dto.setPERSONAid(crew.getPERSONAid().getId());

            dto.setCastList(castService.castEntityList2DTO(crew.getCastList()));

            crewDTOList.add(dto);

        }

        return crewDTOList;
    }

}
