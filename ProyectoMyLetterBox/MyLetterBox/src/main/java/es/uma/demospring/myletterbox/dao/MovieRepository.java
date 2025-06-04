package es.uma.demospring.myletterbox.dao;

import es.uma.demospring.myletterbox.entity.EntityMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<EntityMovie, Integer> {

    @Query("select m from EntityMovie m order by :filtro")
    public List<EntityMovie> ordenarMoviesPorFiltro(@Param("filtro") String filtro);

}
