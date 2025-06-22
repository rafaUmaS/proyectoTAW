package es.uma.demospring.myletterbox.dao;

import es.uma.demospring.myletterbox.dto.PersonaDTO;
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

    @Query("SELECT new es.uma.demospring.myletterbox.dto.PersonaDTO(p.id, p.name, COUNT(c)) " +
            "FROM EntityPersona p LEFT JOIN p.crewList c " +
            "WHERE p.name like concat('%', :nombre,'%') " +
            "GROUP BY p.id, p.name " +
            "ORDER BY COUNT(c) DESC")
    List<PersonaDTO> findPersonasConNumeroPeliculasDesc(@Param("nombre") String nombre);

    @Query("SELECT new es.uma.demospring.myletterbox.dto.PersonaDTO(p.id, p.name, COUNT(c)) " +
            "FROM EntityPersona p LEFT JOIN p.crewList c " +
            "WHERE p.name like concat('%', :nombre,'%') " +
            "GROUP BY p.id, p.name " +
            "ORDER BY COUNT(c) ASC")
    List<PersonaDTO> findPersonasConNumeroPeliculasAsc(@Param("nombre") String nombre);


}
