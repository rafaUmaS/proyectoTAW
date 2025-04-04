package es.uma.demospring.myletterbox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "production_countries_has_movie")
public class EntityProductionCountriesHasMovie {
    @EmbeddedId
    private EntityProductionCountriesHasMovieId id;

    @MapsId("productionCountriesIso31661")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "production_countries_ISO_3166_1", nullable = false)
    private EntityProductionCountry productionCountriesIso31661;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Movie_id", nullable = false)
    private EntityMovie movie;

}