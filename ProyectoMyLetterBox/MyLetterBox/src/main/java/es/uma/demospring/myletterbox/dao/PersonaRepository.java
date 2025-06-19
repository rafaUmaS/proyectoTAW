package es.uma.demospring.myletterbox.dao;

import es.uma.demospring.myletterbox.entity.EntityMovie;
import es.uma.demospring.myletterbox.entity.EntityPersona;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonaRepository extends JpaRepository<EntityPersona, Integer> {


    @Query("select p from EntityPersona p where p.name like concat('%', :nombre, '%') ")
    public List<EntityPersona> buscarPersonaPorFiltro(@Param("nombre") String nombre);

    List<EntityPersona> findByNameContainingIgnoreCase(String nombre, Sort sort);

}
