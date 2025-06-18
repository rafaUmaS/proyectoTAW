package es.uma.demospring.myletterbox.dto;

import lombok.Data;


@Data
public class UsuarioSaveMovieDTO {
    private Integer id;
    private String name;
    private Integer movieId;
    private Integer userId;
}
