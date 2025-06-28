package es.uma.demospring.myletterbox.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
/*
 * Autor(es): Rafael Sáez Arana
 */
@Data
public class UsuarioDTO {
    private Integer userId;
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;
    @NotBlank(message = "El rol es obligatorio")
    private String rol;
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 5, message = "La contraseña debe tener al menos 5 caracteres")
    private String password;
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 4, message = "El nombre de usuario debe tener al menos 4 caracteres")
    private String username;
}
