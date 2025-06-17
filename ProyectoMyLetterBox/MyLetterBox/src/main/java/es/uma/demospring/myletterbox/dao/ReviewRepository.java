package es.uma.demospring.myletterbox.dao;

import es.uma.demospring.myletterbox.entity.EntityReview;
import org.springframework.data.jpa.repository.JpaRepository;
import es.uma.demospring.myletterbox.entity.EntityMovie;
import es.uma.demospring.myletterbox.entity.EntityUsuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface ReviewRepository extends JpaRepository<EntityReview, Integer> {

    @Query("select r from EntityReview r where r.usuarioUserId = :usuario")
    public List<EntityReview> findReviewsByUsuario(@Param("usuario") EntityUsuario usuario);
}