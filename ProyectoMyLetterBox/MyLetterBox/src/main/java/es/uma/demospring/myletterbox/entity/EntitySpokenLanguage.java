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
@Table(name = "spoken_language")
public class EntitySpokenLanguage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ISO_639_1", nullable = true)
    private String iso6391;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "spokenLanguageList")
    private List<EntityMovie> movieList;

    public EntitySpokenLanguage() {
    }

    public EntitySpokenLanguage(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
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
        hash += (iso6391 != null ? iso6391.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntitySpokenLanguage)) {
            return false;
        }
        EntitySpokenLanguage other = (EntitySpokenLanguage) object;
        if ((this.iso6391 == null && other.iso6391 != null) || (this.iso6391 != null && !this.iso6391.equals(other.iso6391))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.uma.demospring.myletterbox.entity.SpokenLanguage[ iso6391=" + iso6391 + " ]";
    }
    
}
