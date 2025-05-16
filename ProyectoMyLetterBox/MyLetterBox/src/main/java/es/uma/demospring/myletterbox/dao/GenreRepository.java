package es.uma.demospring.myletterbox.dao;

import es.uma.demospring.myletterbox.entity.EntityGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<EntityGenre, Integer> {
}
