package es.uma.demospring.myletterbox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "movie")
public class EntityMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "overview", length = 250)
    private String overview;

    @Column(name = "popularity")
    private Double popularity;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "revenue")
    private Integer revenue;

    @Column(name = "runtime")
    private Integer runtime;

    @Lob
    @Column(name = "status")
    private String status;

    @Column(name = "title", length = 45)
    private String title;

    @Column(name = "vote_average")
    private Double voteAverage;

    @Column(name = "vote_count")
    private Integer voteCount;

    @Column(name = "budget")
    private Integer budget;

    @Column(name = "original_language", length = 2)
    private String originalLanguage;

    @Column(name = "original_title", length = 45)
    private String originalTitle;

    @OneToMany(mappedBy = "movieMovie")
    private Set<EntityCrew> crews = new LinkedHashSet<>();

    @ManyToMany
    private Set<EntityGenre> genres = new LinkedHashSet<>();

    @ManyToMany
    private Set<EntityKeyword> keywords = new LinkedHashSet<>();

    @ManyToMany
    private Set<es.uma.demospring.myletterbox.entity.EntitySpokenLanguage> spokenLanguages = new LinkedHashSet<>();

    @ManyToMany
    private Set<es.uma.demospring.myletterbox.entity.EntityProductionCompany> productionCompanies = new LinkedHashSet<>();

    @ManyToMany
    private Set<es.uma.demospring.myletterbox.entity.EntityProductionCountry> productionCountries = new LinkedHashSet<>();

    @OneToMany(mappedBy = "movieMovie")
    private Set<es.uma.demospring.myletterbox.entity.EntityReview> reviews = new LinkedHashSet<>();

    @OneToMany(mappedBy = "movieMovie")
    private Set<es.uma.demospring.myletterbox.entity.EntityUsuarioSaveMovie> usuarioSaveMovies = new LinkedHashSet<>();

}