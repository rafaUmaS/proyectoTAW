package es.uma.demospring.myletterbox.service;

import es.uma.demospring.myletterbox.dao.CrewRepository;
import es.uma.demospring.myletterbox.dto.CrewDTO;
import es.uma.demospring.myletterbox.entity.EntityCrew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrewService extends DTOService<CrewDTO, EntityCrew>{

    @Autowired
    protected CrewRepository crewRepository;

    @Autowired protected CastService castService;

    public List<CrewDTO> listarCrews(){
        List<EntityCrew> crews = crewRepository.findAll();
        return entityCrewList2DTO(crews);
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

            dto.setMovie(crew.getMoviemovieid());

            dto.setPERSONAid(crew.getPERSONAid());

            dto.setCastList(castService.castEntityList2DTO(crew.getCastList()));

            crewDTOList.add(dto);

        }

        return crewDTOList;
    }

}
