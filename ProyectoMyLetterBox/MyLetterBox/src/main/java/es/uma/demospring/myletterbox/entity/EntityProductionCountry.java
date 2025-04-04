package es.uma.demospring.myletterbox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "production_countries")
public class EntityProductionCountry {
    @Id
    @Column(name = "ISO_3166_1", nullable = false, length = 45)
    private String iso31661;

    @Column(name = "name", length = 45)
    private String name;

    @ManyToMany(mappedBy = "productionCountries")
    private Set<EntityMovie> movies = new LinkedHashSet<>();

}