package es.uma.demospring.myletterbox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class EntityUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 16)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false, length = 32)
    private String password;

    @ColumnDefault("'usuario'")
    @Lob
    @Column(name = "rol")
    private String rol;

    @OneToMany(mappedBy = "usuarioUser")
    private Set<EntityReview> reviews = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuarioUser")
    private Set<es.uma.demospring.myletterbox.entity.EntityUsuarioSaveMovie> usuarioSaveMovies = new LinkedHashSet<>();

}