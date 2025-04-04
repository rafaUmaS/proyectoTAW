package es.uma.demospring.myletterbox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "crew")
public class EntityCrew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PERSONA_id", nullable = false)
    private es.uma.demospring.myletterbox.entity.EntityPersona persona;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Movie_movie_id", nullable = false)
    private es.uma.demospring.myletterbox.entity.EntityMovie movieMovie;

    @Column(name = "crew_role", length = 45)
    private String crewRole;

    @OneToMany(mappedBy = "crew")
    private Set<EntityCast> casts = new LinkedHashSet<>();

}