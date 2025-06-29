package es.uma.demospring.myletterbox.dto;

import lombok.Data;

/*
 * Autor(es): Ivan Pedraza Díez (100%)
 */

@Data
public class CastDTO {
    private Integer id;
    private String name;
    private String character;
    private Integer gender;
    private Integer crew;
}
