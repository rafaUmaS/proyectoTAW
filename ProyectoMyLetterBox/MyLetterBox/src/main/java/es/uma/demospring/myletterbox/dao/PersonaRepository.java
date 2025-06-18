package es.uma.demospring.myletterbox.dao;

import es.uma.demospring.myletterbox.entity.EntityPersona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<EntityPersona, Integer> {
}
