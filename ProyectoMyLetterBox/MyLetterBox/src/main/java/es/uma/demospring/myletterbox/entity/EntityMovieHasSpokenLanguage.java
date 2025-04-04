package es.uma.demospring.myletterbox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movie_has_spoken_language")
public class EntityMovieHasSpokenLanguage {
    @EmbeddedId
    private EntityMovieHasSpokenLanguageId id;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Movie_id", nullable = false)
    private EntityMovie movie;

    @MapsId("spokenLanguageIso6391")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Spoken_language_ISO_639_1", nullable = false)
    private es.uma.demospring.myletterbox.entity.EntitySpokenLanguage spokenLanguageIso6391;

}