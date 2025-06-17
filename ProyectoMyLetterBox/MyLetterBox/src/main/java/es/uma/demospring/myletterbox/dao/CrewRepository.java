package es.uma.demospring.myletterbox.dao;

import es.uma.demospring.myletterbox.entity.EntityCrew;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewRepository extends JpaRepository<EntityCrew, Integer> {
}
