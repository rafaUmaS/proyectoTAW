package es.uma.demospring.myletterbox.dto;

import lombok.Data;


@Data
public class MovieDTO {
    private Integer movieId;
    private String name;
    private Double voteAverage;
}
