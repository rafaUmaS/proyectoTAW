package es.uma.demospring.myletterbox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "keywords_has_movie")
public class EntityKeywordsHasMovie {
    @EmbeddedId
    private EntityKeywordsHasMovieId id;

    @MapsId("keywordsId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Keywords_id", nullable = false)
    private EntityKeyword keywords;

    @MapsId("movieMovieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Movie_movie_id", nullable = false)
    private es.uma.demospring.myletterbox.entity.EntityMovie movieMovie;

}