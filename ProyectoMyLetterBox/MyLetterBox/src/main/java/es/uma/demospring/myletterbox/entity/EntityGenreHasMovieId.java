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
public class EntityGenreHasMovieId implements java.io.Serializable {
    private static final long serialVersionUID = 3635055714530284304L;
    @Column(name = "Genre_id", nullable = false)
    private Integer genreId;

    @Column(name = "Movie_id", nullable = false)
    private Integer movieId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EntityGenreHasMovieId entity = (EntityGenreHasMovieId) o;
        return Objects.equals(this.genreId, entity.genreId) &&
                Objects.equals(this.movieId, entity.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreId, movieId);
    }

}