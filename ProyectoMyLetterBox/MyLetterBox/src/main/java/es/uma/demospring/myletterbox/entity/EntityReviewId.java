package es.uma.demospring.myletterbox.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class EntityReviewId implements java.io.Serializable {
    private static final long serialVersionUID = -1054758833353050536L;
    @Column(name = "usuario_user_id", nullable = false)
    private Integer usuarioUserId;

    @Column(name = "movie_movie_id", nullable = false)
    private Integer movieMovieId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EntityReviewId entity = (EntityReviewId) o;
        return Objects.equals(this.movieMovieId, entity.movieMovieId) &&
                Objects.equals(this.usuarioUserId, entity.usuarioUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieMovieId, usuarioUserId);
    }

}