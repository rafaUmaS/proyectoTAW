package es.uma.demospring.myletterbox.controller;

import es.uma.demospring.myletterbox.dao.MovieRepository;
import es.uma.demospring.myletterbox.dao.UsuarioRepository;
import es.uma.demospring.myletterbox.dao.UsuarioSaveMovieRepository;
import es.uma.demospring.myletterbox.dao.ReviewRepository;
import es.uma.demospring.myletterbox.dto.MovieDTO;
import es.uma.demospring.myletterbox.dto.UsuarioSaveMovieDTO;
import es.uma.demospring.myletterbox.entity.EntityMovie;
import es.uma.demospring.myletterbox.entity.EntityReview;
import es.uma.demospring.myletterbox.entity.EntityUsuario;
import es.uma.demospring.myletterbox.entity.EntityUsuarioSaveMovie;

import es.uma.demospring.myletterbox.service.MovieService;
import es.uma.demospring.myletterbox.service.UserMovieService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @Autowired protected ReviewRepository reviewRepository;
    @Autowired protected MovieRepository movieRepository;
    @Autowired private UserMovieService userMovieService;
    @Autowired private MovieService movieService;

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
            List<String> listasRecomendadas = new ArrayList<>(this.userMovieService.obtenerPeliculasRecomendadas().keySet());
            model.addAttribute("listasRecomendadas", listasRecomendadas);
            return "movie";
        }
    }

    // Mostrar películas recomendadas (listar las listas de recomendaciones)
    @GetMapping("/recommended-movies")
    public String doListarPeliculasRecomendadas(HttpSession session, Model model) {
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }

        Map<String, List<UsuarioSaveMovieDTO>> groupedByList = userMovieService.obtenerPeliculasRecomendadas();
        Map<Integer,MovieDTO> movies = movieService.listarMoviesDTO();

        model.addAttribute("groupedByList", groupedByList);
        model.addAttribute("movies", movies);
        return "recommendedMovies";
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

        try {
            userMovieService.recomendarPelicula(movieId, nombreLista, nombreListaBase, crearNueva, usuario);
            return "redirect:/users/movie?id=" + movieId;
        } catch (IllegalArgumentException e) {
            return "redirect:/users/movie?id=" + movieId + "&error=" + e.getMessage();
        }
    }

    @PostMapping("/remove-recommendation")
    public String removeRecommendation(HttpSession session,
                                       @RequestParam("saveMovieId") Integer saveMovieId) {
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }

        userMovieService.eliminarRecomendacion(saveMovieId);
        return "redirect:/users/recommended-movies";
    }

    @GetMapping("/user-reviews")
    public String doListarReviewsUsuario(HttpSession session, Model model){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }else {

            EntityUsuario usuario = (EntityUsuario) session.getAttribute("user");
            List<EntityReview> userReviews = this.reviewRepository.findReviewsByUsuario(usuario);


            model.addAttribute("userReviews", userReviews);

            return "reviewUsuario";
        }
    }
}
