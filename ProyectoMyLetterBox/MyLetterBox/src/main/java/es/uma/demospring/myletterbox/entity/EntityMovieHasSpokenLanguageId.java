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
public class EntityMovieHasSpokenLanguageId implements java.io.Serializable {
    private static final long serialVersionUID = 2791271776330548139L;
    @Column(name = "Movie_id", nullable = false)
    private Integer movieId;

    @Column(name = "Spoken_language_ISO_639_1", nullable = false, length = 45)
    private String spokenLanguageIso6391;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EntityMovieHasSpokenLanguageId entity = (EntityMovieHasSpokenLanguageId) o;
        return Objects.equals(this.spokenLanguageIso6391, entity.spokenLanguageIso6391) &&
                Objects.equals(this.movieId, entity.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spokenLanguageIso6391, movieId);
    }

}