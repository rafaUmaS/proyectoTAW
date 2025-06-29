package es.uma.demospring.myletterbox.dto;

import es.uma.demospring.myletterbox.entity.EntityCrew;
import es.uma.demospring.myletterbox.entity.EntityPersona;
import lombok.Data;

import java.util.List;

/*
 * Autor(es): Ivan Pedraza Díez (100%)
 */

@Data
public class PersonaDTO {
    private Integer id;
    private String name;
    private Long numeroPeliculas;
    private List<Integer> crewList;

    public PersonaDTO(Integer id, String name, Long numeroPeliculas) {
        this.id = id;
        this.name = name;
        this.numeroPeliculas = numeroPeliculas;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public Long getNumeroPeliculas() { return numeroPeliculas; }
}
