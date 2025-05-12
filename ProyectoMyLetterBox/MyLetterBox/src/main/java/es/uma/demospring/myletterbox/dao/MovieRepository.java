package es.uma.demospring.myletterbox.dao;

import es.uma.demospring.myletterbox.entity.EntityMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<EntityMovie, Integer> {
}
