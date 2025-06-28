/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.demospring.myletterbox.entity;

import java.io.Serializable;
import java.util.List;

import es.uma.demospring.myletterbox.dto.CompanieDTO;
import es.uma.demospring.myletterbox.dto.DTO;
import es.uma.demospring.myletterbox.dto.GeneroDTO;
import jakarta.persistence.*;

/**
 *
 * @author guzman
 */
@Entity
@Table(name = "production_companies")
public class EntityProductionCompanies implements Serializable, DTO<CompanieDTO> {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "name", nullable = false)
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @ManyToMany(mappedBy = "productionCompaniesList")
    private List<EntityMovie> movieList;

    public EntityProductionCompanies() {
    }

    public EntityProductionCompanies(Integer id) {
        this.id = id;
    }

    public EntityProductionCompanies(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntityProductionCompanies)) {
            return false;
        }
        EntityProductionCompanies other = (EntityProductionCompanies) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.uma.demospring.myletterbox.entity.ProductionCompanies[ id=" + id + " ]";
    }

    @Override
    public CompanieDTO toDTO(){
        CompanieDTO companie = new CompanieDTO();
        companie.setId(this.id);
        companie.setName(this.name);
        return companie;
    }
}
