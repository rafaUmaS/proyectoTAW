/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.demospring.myletterbox.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author guzman
 */
@Entity
@Table(name = "usuario")
public class EntityUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "username", nullable = false)
    private String username;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @Column(name = "email", nullable = false)
    private String email;
    @Basic(optional = false)
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "rol")
    private String rol;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioUserId")
    private List<EntityUsuarioSaveMovie> usuarioSaveMovieList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioUserId")
    private List<EntityReview> reviewList;

    public EntityUsuario() {
    }

    public EntityUsuario(Integer userId) {
        this.userId = userId;
    }

    public EntityUsuario(Integer userId, String username, String email, String password) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<EntityUsuarioSaveMovie> getUsuarioSaveMovieList() {
        return usuarioSaveMovieList;
    }

    public void setUsuarioSaveMovieList(List<EntityUsuarioSaveMovie> usuarioSaveMovieList) {
        this.usuarioSaveMovieList = usuarioSaveMovieList;
    }

    public List<EntityReview> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<EntityReview> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntityUsuario)) {
            return false;
        }
        EntityUsuario other = (EntityUsuario) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.uma.demospring.myletterbox.entity.Usuario[ userId=" + userId + " ]";
    }
    
}
