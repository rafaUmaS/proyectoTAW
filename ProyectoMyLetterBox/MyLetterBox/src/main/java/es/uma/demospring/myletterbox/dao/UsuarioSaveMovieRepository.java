package es.uma.demospring.myletterbox.dao;

import es.uma.demospring.myletterbox.entity.EntityUsuario;
import es.uma.demospring.myletterbox.entity.EntityUsuarioSaveMovie;
import es.uma.demospring.myletterbox.entity.EntityUsuarioSaveMovieId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioSaveMovieRepository extends JpaRepository<EntityUsuarioSaveMovie, EntityUsuarioSaveMovieId> {
    @Query("select um from EntityUsuarioSaveMovie um where um.usuarioUser.id=:usuarioId")
    public List<EntityUsuarioSaveMovie> listaUsuarioSaveMovie(@Param("usuarioId") Integer userId);
}
