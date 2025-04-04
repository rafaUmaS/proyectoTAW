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
public class EntityKeywordsHasMovieId implements java.io.Serializable {
    private static final long serialVersionUID = -3282920405587726220L;
    @Column(name = "Keywords_id", nullable = false)
    private Integer keywordsId;

    @Column(name = "Movie_movie_id", nullable = false)
    private Integer movieMovieId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EntityKeywordsHasMovieId entity = (EntityKeywordsHasMovieId) o;
        return Objects.equals(this.keywordsId, entity.keywordsId) &&
                Objects.equals(this.movieMovieId, entity.movieMovieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keywordsId, movieMovieId);
    }

}