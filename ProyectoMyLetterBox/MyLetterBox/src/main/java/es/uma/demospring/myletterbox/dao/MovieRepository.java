package es.uma.demospring.myletterbox.dao;

import es.uma.demospring.myletterbox.entity.EntityMovie;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<EntityMovie, Integer> {



    @Query("select m from EntityMovie m where m.name like concat('%', :nombre, '%') ")
    public List<EntityMovie> buscarMoviePorFiltro(@Param("nombre") String nombre);

    List<EntityMovie> findByNameContainingIgnoreCase(String nombre, Sort sort);

}
