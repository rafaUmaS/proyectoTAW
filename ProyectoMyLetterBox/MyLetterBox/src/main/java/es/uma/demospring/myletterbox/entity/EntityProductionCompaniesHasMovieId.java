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
public class EntityProductionCompaniesHasMovieId implements java.io.Serializable {
    private static final long serialVersionUID = -8425973815205606632L;
    @Column(name = "Production_companies_id", nullable = false)
    private Integer productionCompaniesId;

    @Column(name = "Movie_movie_id", nullable = false)
    private Integer movieMovieId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EntityProductionCompaniesHasMovieId entity = (EntityProductionCompaniesHasMovieId) o;
        return Objects.equals(this.movieMovieId, entity.movieMovieId) &&
                Objects.equals(this.productionCompaniesId, entity.productionCompaniesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieMovieId, productionCompaniesId);
    }

}