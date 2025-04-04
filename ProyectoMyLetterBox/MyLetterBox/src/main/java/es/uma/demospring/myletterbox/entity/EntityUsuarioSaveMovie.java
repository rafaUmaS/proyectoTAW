package es.uma.demospring.myletterbox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario_save_movie")
public class EntityUsuarioSaveMovie {
    @EmbeddedId
    private EntityUsuarioSaveMovieId id;

    @MapsId("usuarioUserId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_user_id", nullable = false)
    private EntityUsuario usuarioUser;

    @MapsId("movieMovieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_movie_id", nullable = false)
    private EntityMovie movieMovie;

    @Column(name = "name", length = 45)
    private String name;

}