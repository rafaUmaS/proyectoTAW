package es.uma.demospring.myletterbox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "keywords")
public class EntityKeyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @ManyToMany
    @JoinTable(name = "keywords_has_movie",
            joinColumns = @JoinColumn(name = "Keywords_id"),
            inverseJoinColumns = @JoinColumn(name = "Movie_movie_id"))
    private Set<es.uma.demospring.myletterbox.entity.EntityMovie> movies = new LinkedHashSet<>();

}