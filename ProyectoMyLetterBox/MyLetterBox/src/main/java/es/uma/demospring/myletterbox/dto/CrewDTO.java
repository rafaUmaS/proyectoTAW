package es.uma.demospring.myletterbox.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CrewDTO {
    private Integer id;
    private String crewRole;
    private List<CastDTO> castList;
    private List<Integer> castIds;
    private Integer movieId;
    private Integer pERSONAid;
    private String nombrePersona;
}
