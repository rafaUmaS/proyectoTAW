package es.uma.demospring.myletterbox.dao;

import es.uma.demospring.myletterbox.entity.EntityMovie;
import es.uma.demospring.myletterbox.entity.EntityUsuario;
import es.uma.demospring.myletterbox.entity.EntityUsuarioSaveMovie;
import es.uma.demospring.myletterbox.entity.EntityUsuarioSaveMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * Autor(es): Ivan Pedraza Díez (15%), Adrián Huete Peña(85%)
 */

public interface UsuarioSaveMovieRepository extends JpaRepository<EntityUsuarioSaveMovie, Integer> {
    @Query("select um from EntityUsuarioSaveMovie um where um.usuarioUserId.userId=:usuarioId")
    List<EntityUsuarioSaveMovie> listaUsuarioSaveMovie(@Param("usuarioId") Integer userId);

    // Obtener todas las listas únicas que contengan cierto texto
    @Query("SELECT DISTINCT um.name FROM EntityUsuarioSaveMovie um WHERE um.name LIKE %:fragmento%")
    List<String> findDistinctListaNamesContaining(@Param("fragmento") String fragmento);
    // Obtener todas las peliculas recomendadas
    @Query("SELECT um FROM EntityUsuarioSaveMovie um WHERE um.name LIKE %:fragmento%")
    List<EntityUsuarioSaveMovie> findAllByNameContaining(@Param("fragmento") String fragmento);

    // Obtener todas las películas de una lista específica
    @Query("SELECT um FROM EntityUsuarioSaveMovie um WHERE um.name = :nombreLista")
    List<EntityUsuarioSaveMovie> obtenerPeliculasPorNombreLista(@Param("nombreLista") String nombreLista);

    //Comprueba que una lista no es igual o parecida a otra lista ya existente
    @Query("SELECT COUNT(u) > 0 FROM EntityUsuarioSaveMovie u WHERE LOWER(REPLACE(u.name, ' ', '')) = LOWER(REPLACE(:name, ' ', ''))")
    boolean findListaByNombreNormalizado(@Param("name") String nombreNormalizado);


    //Comprueba si existe una pelicula en una lista para evitar duplicados
    @Query("SELECT COUNT(u) > 0 FROM EntityUsuarioSaveMovie u WHERE u.usuarioUserId = :usuario AND u.movieMovieId = :movie AND u.name = :name")
    boolean existsByUsuarioAndMovieAndName(@Param("usuario") EntityUsuario usuario,
                                           @Param("movie") EntityMovie movie,
                                           @Param("name") String name);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuario_save_movie (movie_movie_id, usuario_user_id, name) VALUES (:movieId, :userId, :name)", nativeQuery = true)
    void insertRelation(@Param("movieId") Integer movieId, @Param("userId") Integer userId, @Param("name") String name);

}
