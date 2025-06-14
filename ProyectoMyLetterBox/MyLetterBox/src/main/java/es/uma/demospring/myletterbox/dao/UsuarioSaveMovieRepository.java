package es.uma.demospring.myletterbox.dao;

import es.uma.demospring.myletterbox.entity.EntityUsuario;
import es.uma.demospring.myletterbox.entity.EntityUsuarioSaveMovie;
import es.uma.demospring.myletterbox.entity.EntityUsuarioSaveMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*
 * Autor(es): Ivan Pedraza Díez (100%)
 */

public interface UsuarioSaveMovieRepository extends JpaRepository<EntityUsuarioSaveMovie, Integer> {
    @Query("select um from EntityUsuarioSaveMovie um where um.usuarioUserId.userId=:usuarioId")
    public List<EntityUsuarioSaveMovie> listaUsuarioSaveMovie(@Param("usuarioId") Integer userId);
}
