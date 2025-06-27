package es.uma.demospring.myletterbox.dto;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Integer userId;
    private String email;
    private String rol;
    private String password;
    private String username;
}
