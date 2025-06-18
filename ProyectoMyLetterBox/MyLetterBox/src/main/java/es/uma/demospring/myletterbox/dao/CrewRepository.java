package es.uma.demospring.myletterbox.dao;

import es.uma.demospring.myletterbox.entity.EntityCrew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CrewRepository extends JpaRepository<EntityCrew, Integer> {

}
