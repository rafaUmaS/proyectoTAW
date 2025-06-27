/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.demospring.myletterbox.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.uma.demospring.myletterbox.dto.CrewDTO;
import es.uma.demospring.myletterbox.dto.DTO;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author guzman
 */
@Entity
@Table(name = "crew")
public class EntityCrew implements Serializable, DTO<CrewDTO> {

    private static final long serialVersionUID = 1L;
    @Column(name = "crew_role")
    private String crewRole;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crewid")
    private List<EntityCast> castList;
    @JoinColumn(name = "Movie_movie_id", referencedColumnName = "movie_id")
    @ManyToOne(optional = false)
    private EntityMovie moviemovieid;
    @JoinColumn(name = "PERSONA_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EntityPersona pERSONAid;

    public EntityCrew() {
    }

    public EntityCrew(Integer id) {
        this.id = id;
    }

    public String getCrewRole() {
        return crewRole;
    }

    public void setCrewRole(String crewRole) {
        this.crewRole = crewRole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<EntityCast> getCastList() {
        return castList;
    }

    public void setCastList(List<EntityCast> castList) {
        this.castList = castList;
    }

    public EntityMovie getMoviemovieid() {
        return moviemovieid;
    }

    public void setMoviemovieid(EntityMovie moviemovieid) {
        this.moviemovieid = moviemovieid;
    }

    public EntityPersona getPERSONAid() {
        return pERSONAid;
    }

    public void setPERSONAid(EntityPersona pERSONAid) {
        this.pERSONAid = pERSONAid;
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
        if (!(object instanceof EntityCrew)) {
            return false;
        }
        EntityCrew other = (EntityCrew) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.uma.demospring.myletterbox.entity.Crew[ id=" + id + " ]";
    }

    @Override
    public CrewDTO toDTO(){
        CrewDTO dto = new CrewDTO();
        dto.setId(this.id);
        dto.setCrewRole(this.crewRole);
        dto.setMovieId(this.moviemovieid.getMovieId());
        dto.setPERSONAid(this.pERSONAid.getId());
        dto.setNombrePersona(this.getPERSONAid().getName());
        List<Integer> castIds = new ArrayList<>();
        for(EntityCast cast : castList){
            castIds.add(cast.getId());
        }
        dto.setCastIds(castIds);

        return dto;
    }
    
}
