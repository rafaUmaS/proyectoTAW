package es.uma.demospring.myletterbox.dao;

import es.uma.demospring.myletterbox.entity.EntityReview;
import org.springframework.data.jpa.repository.JpaRepository;
import es.uma.demospring.myletterbox.entity.EntityMovie;
import es.uma.demospring.myletterbox.entity.EntityUsuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

// Autor(es): Gregorio Merchán Merchán (100%)

public interface ReviewRepository extends JpaRepository<EntityReview, Integer> {

    @Query("SELECT r FROM EntityReview r WHERE r.usuarioUserId = :usuario")
    List<EntityReview> findReviewsByUsuario(@Param("usuario") EntityUsuario usuario);
}