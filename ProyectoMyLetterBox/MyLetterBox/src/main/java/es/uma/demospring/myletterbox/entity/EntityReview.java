package es.uma.demospring.myletterbox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "review")
public class EntityReview {
    @EmbeddedId
    private EntityReviewId id;

    @MapsId("usuarioUserId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_user_id", nullable = false)
    private es.uma.demospring.myletterbox.entity.EntityUsuario usuarioUser;

    @MapsId("movieMovieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_movie_id", nullable = false)
    private EntityMovie movieMovie;

    @Column(name = "comment", length = 250)
    private String comment;

    @Column(name = "rate")
    private Double rate;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "create_time")
    private Instant createTime;

}