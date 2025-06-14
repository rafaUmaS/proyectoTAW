package es.uma.demospring.myletterbox.ui;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/*
 * Autor(es):
 */

@Data
public class Movie {
    public enum Estado
    {
        RELEASED, RUMORED, POST_PRODUCTION
    }

    private int id;
    private String titulo;
    private String tituloOriginal;
    private String idioma;
    private String descripcion;
    private Double popularidad;
    private Integer beneficios;
    private Integer presupuesto;
    private Integer duracion;
    private Integer numVotos;
    private Double mediaVotos;
    private List<Integer> generos;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    private String estado;
}
