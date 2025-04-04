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
public class EntityProductionCountriesHasMovieId implements java.io.Serializable {
    private static final long serialVersionUID = -7208712116675164054L;
    @Column(name = "production_countries_ISO_3166_1", nullable = false, length = 45)
    private String productionCountriesIso31661;

    @Column(name = "Movie_id", nullable = false)
    private Integer movieId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EntityProductionCountriesHasMovieId entity = (EntityProductionCountriesHasMovieId) o;
        return Objects.equals(this.movieId, entity.movieId) &&
                Objects.equals(this.productionCountriesIso31661, entity.productionCountriesIso31661);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, productionCountriesIso31661);
    }

}