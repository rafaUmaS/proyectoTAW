package es.uma.demospring.myletterbox.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/*
 * Autor(es): Álvaro Sierra García (100%)
 */

@Data
public class CountryDTO {
    @NotBlank(message = "Código Iso31661 obligatorio de 2 caracteres.")
    @Pattern(regexp = "^$|^[a-zA-Z]{2}$", message = "El Iso31661 debe tener almenos 2 caracteres (ej: 'es', 'en')")
    private String iso31661;

    @NotBlank(message = "Nombre del Pais obligatorio.")
    private String name;
}
