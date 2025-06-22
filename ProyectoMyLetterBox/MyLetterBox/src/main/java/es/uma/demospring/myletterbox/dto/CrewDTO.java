package es.uma.demospring.myletterbox.dto;

import es.uma.demospring.myletterbox.entity.EntityMovie;
import es.uma.demospring.myletterbox.entity.EntityPersona;
import lombok.Data;

import java.util.List;

@Data
public class CrewDTO {

    private Integer id;

    private String crewRole;

    private List<CastDTO> castList;

    private EntityMovie movie;

    private EntityPersona pERSONAid;
}
