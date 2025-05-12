/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.demospring.myletterbox.entity;

import java.io.Serializable;
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
@Table(name = "usuario_save_movie")
public class EntityUsuarioSaveMovie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "name")
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "movie_movie_id", referencedColumnName = "movie_id")
    @ManyToOne(optional = false)
    private EntityMovie movieMovieId;
    @JoinColumn(name = "usuario_user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private EntityUsuario usuarioUserId;

    public EntityUsuarioSaveMovie() {
    }

    public EntityUsuarioSaveMovie(Integer id) {
        this.id = id;
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

    public EntityMovie getMovieMovieId() {
        return movieMovieId;
    }

    public void setMovieMovieId(EntityMovie movieMovieId) {
        this.movieMovieId = movieMovieId;
    }

    public EntityUsuario getUsuarioUserId() {
        return usuarioUserId;
    }

    public void setUsuarioUserId(EntityUsuario usuarioUserId) {
        this.usuarioUserId = usuarioUserId;
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
        if (!(object instanceof EntityUsuarioSaveMovie)) {
            return false;
        }
        EntityUsuarioSaveMovie other = (EntityUsuarioSaveMovie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.uma.demospring.myletterbox.entity.UsuarioSaveMovie[ id=" + id + " ]";
    }
    
}
