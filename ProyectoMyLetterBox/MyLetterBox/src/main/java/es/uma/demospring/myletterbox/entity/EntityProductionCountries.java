/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.demospring.myletterbox.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 *
 * @author guzman
 */
@Entity
@Table(name = "production_countries")
public class EntityProductionCountries implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ISO_3166_1", nullable = false)
    private String iso31661;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "productionCountriesList")
    private List<EntityMovie> movieList;

    public EntityProductionCountries() {
    }

    public EntityProductionCountries(String iso31661) {
        this.iso31661 = iso31661;
    }

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EntityMovie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<EntityMovie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iso31661 != null ? iso31661.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntityProductionCountries)) {
            return false;
        }
        EntityProductionCountries other = (EntityProductionCountries) object;
        if ((this.iso31661 == null && other.iso31661 != null) || (this.iso31661 != null && !this.iso31661.equals(other.iso31661))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.uma.demospring.myletterbox.entity.ProductionCountries[ iso31661=" + iso31661 + " ]";
    }
    
}
