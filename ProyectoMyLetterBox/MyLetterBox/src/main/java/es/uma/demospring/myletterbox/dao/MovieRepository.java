package es.uma.demospring.myletterbox.dao;

import es.uma.demospring.myletterbox.entity.EntityMovie;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<EntityMovie, Integer> {


    @Query("SELECT DISTINCT m FROM EntityMovie m JOIN m.genreList g " +
            "WHERE (:nombre IS NULL OR LOWER(m.title) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
            "AND (:idioma IS NULL OR LOWER(m.originalLanguage) LIKE LOWER(CONCAT('%', :idioma, '%'))) " +
            "AND (:genero IS NULL OR LOWER(g.name) LIKE LOWER(CONCAT('%', :genero, '%')))")
    List<EntityMovie> filtrarPeliculas(
            @Param("nombre") String nombre,
            @Param("idioma") String idioma,
            @Param("genero") String genero,
            Sort sort);

    @Query("SELECT DISTINCT m FROM EntityMovie m JOIN m.genreList g " +
            "WHERE (:nombre IS NULL OR LOWER(m.title) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
            "AND (:idioma IS NULL OR LOWER(m.originalLanguage) LIKE LOWER(CONCAT('%', :idioma, '%'))) " +
            "AND (:genero IS NULL OR LOWER(g.name) LIKE LOWER(CONCAT('%', :genero, '%')))")
    List<EntityMovie> filtrarPeliculas(
            @Param("nombre") String nombre,
            @Param("idioma") String idioma,
            @Param("genero") String genero);


}
