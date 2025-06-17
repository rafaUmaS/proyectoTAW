package es.uma.demospring.myletterbox.dao;

import es.uma.demospring.myletterbox.entity.EntityCast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CastRepository extends JpaRepository<EntityCast, Integer> {
}
