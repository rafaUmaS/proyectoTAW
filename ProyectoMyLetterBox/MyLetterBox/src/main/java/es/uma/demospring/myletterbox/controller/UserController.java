package es.uma.demospring.myletterbox.controller;

import es.uma.demospring.myletterbox.dao.MovieRepository;
import es.uma.demospring.myletterbox.dao.UsuarioRepository;
import es.uma.demospring.myletterbox.dao.UsuarioSaveMovieRepository;
import es.uma.demospring.myletterbox.entity.EntityMovie;
import es.uma.demospring.myletterbox.entity.EntityUsuario;
import es.uma.demospring.myletterbox.entity.EntityUsuarioSaveMovie;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Autor(es): Ivan Pedraza Díez (20%), Gregorio Merchán Merchán(20%), Adrián Huete Peña(60%)
 */

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {


    @Autowired protected UsuarioRepository usuarioRepository;
    @Autowired protected UsuarioSaveMovieRepository usuarioSaveMovieRepository;
    @Autowired protected MovieRepository movieRepository;



    @GetMapping("/saved-movies")
    public String doListarSavedMovieUsuario(HttpSession session, Model model){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }else {

            int usuarioID = ((EntityUsuario)session.getAttribute("user")).getUserId();
            List<EntityUsuarioSaveMovie> savedMovies = this.usuarioSaveMovieRepository.listaUsuarioSaveMovie(
                    usuarioID);

            model.addAttribute("savedMovies", savedMovies);

            return "usuarioMovies";
        }
    }



    @GetMapping("/movie")
    public String doMostrarMovie(HttpSession session,Integer id, Model model){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }else {
            EntityMovie movie =  this.movieRepository.findById(id).orElse(null);
            model.addAttribute("movie", movie);

            // Añadimos listas únicas con nombre que contiene " Recomendado"
            List<String> listasRecomendadas = this.usuarioSaveMovieRepository.findDistinctListaNamesContaining(" Recomendado");
            model.addAttribute("listasRecomendadas", listasRecomendadas);
            return "movie";
        }
    }

    // Mostrar películas recomendadas (listar las listas de recomendaciones)
    @GetMapping("/recommended-movies")
    public String doListarPeliculasRecomendadas(HttpSession session, Model model){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        } else {
            // Solo obtener películas con nombre de lista que contenga "Recomendado"
            List<EntityUsuarioSaveMovie> savedMovies = this.usuarioSaveMovieRepository.findAllByNameContaining("Recomendado");

            // Agrupar por nombre de lista
            Map<String, List<EntityUsuarioSaveMovie>> groupedByList = new HashMap<>();
            for (EntityUsuarioSaveMovie saveMovie : savedMovies) {
                String listaNombre = saveMovie.getName();
                groupedByList.computeIfAbsent(listaNombre, k -> new ArrayList<>()).add(saveMovie);
            }

            model.addAttribute("groupedByList", groupedByList);

            return "recommendedMovies";
        }
    }
    // Recomendar una película y asignarla a una lista
    @PostMapping("/recomendar")
    public String doRecomendarPelicula(HttpSession session,
                                       @RequestParam("movieId") Integer movieId,
                                       @RequestParam(required = false) String nombreLista,
                                       @RequestParam(required = false) String nombreListaBase,
                                       @RequestParam(required = false) String crearNueva) {
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }

        EntityUsuario usuario = (EntityUsuario) session.getAttribute("user");
        EntityMovie movie = movieRepository.findById(movieId).orElse(null);

        if (movie == null) {
            return "redirect:/users/movie";
        }

        String nombreFinalLista;

        // Caso: crear nueva lista
        if (crearNueva.equals("true")) {
            if (nombreListaBase == null || nombreListaBase.trim().isEmpty()) {
                return "redirect:/users/movie?id=" + movieId; // podrías mostrar un error
            }
            nombreFinalLista = nombreListaBase.trim() + " Recomendado";
            // Normalizar el nombre para comparar
            String nombreFinalListaNormalizado = nombreFinalLista.toLowerCase().replaceAll("\\s+", "");

            // Buscar si ya existe una lista parecida
            boolean listasSimilares = usuarioSaveMovieRepository.findListaByNombreNormalizado(nombreFinalLista);

            if (listasSimilares) {
                // Ya existe una lista con nombre similar
                return "redirect:/users/movie?id=" + movieId + "&error=nombreDuplicado";
            }
        } else {
            // Usar lista existente
            nombreFinalLista = nombreLista;
        }


        //Comprobar que no haya duplicados
        boolean res = usuarioSaveMovieRepository.existsByUsuarioAndMovieAndName(usuario, movie, nombreFinalLista);
        if(res){
            return "redirect:/users/movie?id=" + movieId + "&error=yaExiste";
        }
        // Crear y guardar recomendación
        EntityUsuarioSaveMovie saveMovie = new EntityUsuarioSaveMovie();
        saveMovie.setName(nombreFinalLista);
        saveMovie.setMovieMovieId(movie);
        saveMovie.setUsuarioUserId(usuario);
        usuarioSaveMovieRepository.save(saveMovie);

        return "redirect:/users/movie?id=" + movieId; // Volver a la película
    }

    @PostMapping("/remove-recommendation")
    public String removeRecommendation(HttpSession session,
                                       @RequestParam("saveMovieId") Integer saveMovieId) {
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }

        // Buscar y borrar la relación de película con lista recomendada
        usuarioSaveMovieRepository.deleteById(saveMovieId);

        return "redirect:/users/recommended-movies";
    }
}
