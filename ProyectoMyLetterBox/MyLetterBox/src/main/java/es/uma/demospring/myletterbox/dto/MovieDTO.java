package es.uma.demospring.myletterbox.dto;

import es.uma.demospring.myletterbox.entity.EntityGenre;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@Data
public class MovieDTO {

    public enum Estado
    {
        RELEASED, RUMORED, POST_PRODUCTION
    }

    private Integer movieId;

    @NotBlank(message = "El título es obligatorio")
    private String name;

    private String originalTittle;

    @Pattern(regexp = "^$|^[a-zA-Z]{2,}$", message = "El idioma debe contener solo letras (ej: 'es', 'en')")
    private String language;

    private String description;

    @DecimalMin(value = "0.0", message = "La popularidad debe ser positiva")
    @DecimalMax(value = "100.0", message = "La popularidad no puede ser mayor que 10")
    private Double popularity;

    private Integer revenue;

    @PositiveOrZero(message = "El presupuesto no puede ser negativo")
    private Integer budget;

    @Min(value = 1, message = "La duración debe ser mayor que 0")
    private Integer duration;

    @Min(value = 0, message = "El número de votos debe ser positivo")
    private Integer voteNumber;

    @DecimalMin(value = "0.0", message = "La media de votos debe ser positiva")
    @DecimalMax(value = "10.0", message = "La media de votos no puede ser mayor que 10")
    private Double voteAverage;

    @NotEmpty(message = "Selecciona al menos un género")
    private List<Integer> generos;

    @NotNull(message = "Selecciona una fecha válida")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String estado;

    private List<GeneroDTO> generosDTO;
}
