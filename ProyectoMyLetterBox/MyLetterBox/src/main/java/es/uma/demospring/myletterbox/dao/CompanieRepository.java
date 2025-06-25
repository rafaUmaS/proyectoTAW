package es.uma.demospring.myletterbox.dao;

import es.uma.demospring.myletterbox.entity.EntityProductionCompanies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanieRepository extends JpaRepository <EntityProductionCompanies, Integer> {
}
