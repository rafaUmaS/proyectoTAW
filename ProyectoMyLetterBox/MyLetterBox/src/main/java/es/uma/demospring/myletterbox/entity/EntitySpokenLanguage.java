package es.uma.demospring.myletterbox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "spoken_language")
public class EntitySpokenLanguage {
    @Id
    @Column(name = "ISO_639_1", nullable = false, length = 45)
    private String iso6391;

    @Column(name = "name", length = 45)
    private String name;

    @ManyToMany(mappedBy = "spokenLanguages")
    private Set<EntityMovie> movies = new LinkedHashSet<>();

}