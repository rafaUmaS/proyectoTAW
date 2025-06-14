package es.uma.demospring.myletterbox.dao;

import es.uma.demospring.myletterbox.entity.EntityUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/*
 * Autor(es): Ivan Pedraza Díez (100%)
 */

public interface UsuarioRepository extends JpaRepository<EntityUsuario, Integer> {

    @Query("select u from EntityUsuario u where u.username=:usuario and u.password=:password")
    public EntityUsuario autenticaUsuario(@Param("usuario") String usuario, @Param("password") String password);
}
