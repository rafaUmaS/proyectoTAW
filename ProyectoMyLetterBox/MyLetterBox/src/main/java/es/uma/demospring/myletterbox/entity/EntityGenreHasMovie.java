package es.uma.demospring.myletterbox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "genre_has_movie")
public class EntityGenreHasMovie {
    @EmbeddedId
    private EntityGenreHasMovieId id;

    @MapsId("genreId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Genre_id", nullable = false)
    private EntityGenre genre;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Movie_id", nullable = false)
    private es.uma.demospring.myletterbox.entity.EntityMovie movie;

}