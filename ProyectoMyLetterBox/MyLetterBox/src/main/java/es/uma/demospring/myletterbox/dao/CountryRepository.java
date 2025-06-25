package es.uma.demospring.myletterbox.dao;

import es.uma.demospring.myletterbox.entity.EntityProductionCountries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<EntityProductionCountries, String> {
}
