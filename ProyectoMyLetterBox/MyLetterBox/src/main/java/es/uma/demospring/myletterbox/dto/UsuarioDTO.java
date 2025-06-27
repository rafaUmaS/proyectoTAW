package es.uma.demospring.myletterbox.dto;
import lombok.Data;
/*
 * Autor(es): Rafael Sáez Arana
 */
@Data
public class UsuarioDTO {
    private Integer userId;
    private String email;
    private String rol;
    private String password;
    private String username;
}
