package es.uma.demospring.myletterbox.dto;

import es.uma.demospring.myletterbox.entity.EntityCrew;
import lombok.Data;

import java.util.List;

@Data
public class PersonaDTO {
    private Integer id;
    private String name;
    private Long numeroPeliculas;
    private List<EntityCrew> crewList;

    public PersonaDTO(Integer id, String name, Long numeroPeliculas) {
        this.id = id;
        this.name = name;
        this.numeroPeliculas = numeroPeliculas;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public Long getNumeroPeliculas() { return numeroPeliculas; }
}
