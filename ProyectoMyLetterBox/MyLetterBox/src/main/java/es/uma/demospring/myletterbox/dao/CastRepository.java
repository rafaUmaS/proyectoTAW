package es.uma.demospring.myletterbox.dao;

import es.uma.demospring.myletterbox.entity.EntityCast;
import es.uma.demospring.myletterbox.entity.EntityCrew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CastRepository extends JpaRepository<EntityCast, Integer> {
    @Query("SELECT c FROM EntityCast c WHERE c.crewid = :crew")
    List<EntityCast> findByCrewid(@Param("crew") EntityCrew crew);
}
