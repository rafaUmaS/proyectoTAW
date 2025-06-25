/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.demospring.myletterbox.entity;

import java.io.Serializable;

import es.uma.demospring.myletterbox.dto.DTO;
import es.uma.demospring.myletterbox.dto.CastDTO;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author guzman
 */
@Entity
@Table(name = "cast")
public class EntityCast implements Serializable, DTO<CastDTO> {

    private static final long serialVersionUID = 1L;
    @Column(name = "character_name")
    private String character;
    @Column(name = "gender")
    private Integer gender;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "Crew_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EntityCrew crewid;

    public EntityCast() {
    }

    public EntityCast(Integer id) {
        this.id = id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityCrew getCrewid() {
        return crewid;
    }

    public void setCrewid(EntityCrew crewid) {
        this.crewid = crewid;
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
        if (!(object instanceof EntityCast)) {
            return false;
        }
        EntityCast other = (EntityCast) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.uma.demospring.myletterbox.entity.Cast[ id=" + id + " ]";
    }

    @Override
    public CastDTO toDTO() {
        CastDTO dto = new CastDTO();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setGender(this.gender);
        dto.setCharacter(this.character);
        return dto;
    }

}
