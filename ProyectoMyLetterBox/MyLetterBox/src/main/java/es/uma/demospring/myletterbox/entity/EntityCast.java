package es.uma.demospring.myletterbox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cast")
public class EntityCast {
    @EmbeddedId
    private EntityCastId id;

    @MapsId("crewId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Crew_id", nullable = false)
    private es.uma.demospring.myletterbox.entity.EntityCrew crew;

    @Column(name = "`character`", length = 45)
    private String character;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "name", length = 45)
    private String name;

}