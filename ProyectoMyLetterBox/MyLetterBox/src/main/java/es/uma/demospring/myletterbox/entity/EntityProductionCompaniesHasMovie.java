package es.uma.demospring.myletterbox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "production_companies_has_movie")
public class EntityProductionCompaniesHasMovie {
    @EmbeddedId
    private EntityProductionCompaniesHasMovieId id;

    @MapsId("productionCompaniesId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Production_companies_id", nullable = false)
    private EntityProductionCompany productionCompanies;

    @MapsId("movieMovieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Movie_movie_id", nullable = false)
    private EntityMovie movieMovie;

}