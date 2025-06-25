package es.uma.demospring.myletterbox.controller;

import es.uma.demospring.myletterbox.dao.GenreRepository;
import es.uma.demospring.myletterbox.dao.MovieRepository;
import es.uma.demospring.myletterbox.dao.UsuarioSaveMovieRepository;
import es.uma.demospring.myletterbox.dao.ReviewRepository;
import es.uma.demospring.myletterbox.dto.GeneroDTO;
import es.uma.demospring.myletterbox.dto.MovieDTO;
import es.uma.demospring.myletterbox.entity.EntityGenre;
import es.uma.demospring.myletterbox.entity.EntityMovie;
import es.uma.demospring.myletterbox.entity.EntityUsuario;
import es.uma.demospring.myletterbox.entity.EntityReview;
import es.uma.demospring.myletterbox.entity.EntityUsuarioSaveMovie;
import es.uma.demospring.myletterbox.service.GeneroService;
import es.uma.demospring.myletterbox.service.MovieService;
import es.uma.demospring.myletterbox.ui.Movie;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
 * Autor(es): Álvaro Sierra García (80%)
 */

@Controller
@RequestMapping("/movies")
public class MovieController extends BaseController {

    @Autowired protected MovieService movieService;

    @Autowired protected GeneroService generoService;

    @Autowired
    protected MovieRepository movieRepository;

    @Autowired
    protected GenreRepository genreRepository;

    @Autowired
    private UsuarioSaveMovieRepository usuarioSaveMovieRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/")
    public String doListarMovies(HttpSession session, Model model){

        if(!estaAutenticado(session)){
            return "redirect:/";
        }else {

            List<EntityMovie> listaPeliculas = this.movieRepository.findAll();

            model.addAttribute("listaPeliculas", listaPeliculas);

            return "allMovies";
        }
    }

    @GetMapping("/crear")
    public String crearMovie(HttpSession session, Model model){
        if(!estaAutenticado(session)){
            return "redirect:/";
        } else {
            model.addAttribute("pelicula", new MovieDTO());
            model.addAttribute("generos", this.generoService.listarGeneros());
            model.addAttribute("esCrear", true);
            model.addAttribute("estados", MovieDTO.Estado.values());
            return "editor/editMovie";
        }
    }

    @PostMapping("/borrar")
    public String borrarMovie(HttpSession session, @RequestParam("id") int id){
        if(!estaAutenticado(session)){
            return "redirect:/";
        } else {
            this.movieService.borrarMovieById(id);
            return "redirect:/movies/";
        }
    }

    @GetMapping("/editar")
    public String doEditar(@RequestParam("id") Integer id,
                           HttpSession session,
                           Model model){
        if(!estaAutenticado(session)){
            return "redirect:/";
        } else {
            MovieDTO movie = this.movieService.getMovieDTOById(id);
            List<GeneroDTO> generos = this.generoService.listarGeneros();

            model.addAttribute("pelicula", movie);
            model.addAttribute("esCrear", false);
            model.addAttribute("generos", generos);
            model.addAttribute("estados", MovieDTO.Estado.values());

            return "editor/editMovie";
        }
    }

    @PostMapping("/guardar")
    public String doGuardar(@Valid @ModelAttribute("pelicula") MovieDTO pelicula,
                            BindingResult result,
                            HttpSession session,
                            Model model){
        if(!estaAutenticado(session)){
            return "redirect:/";
        } else {
            if (result.hasErrors()) {
                List<GeneroDTO> generos = this.generoService.listarGeneros();
                model.addAttribute("pelicula", pelicula);
                model.addAttribute("esCrear", false);
                model.addAttribute("generos", generos);
                model.addAttribute("estados", MovieDTO.Estado.values());
                return "editor/editMovie";
            }

            this.movieService.guardarMovie(pelicula);
            return "redirect:/movies/";
        }
    }

    @PostMapping("/like")
    public String doLikeMovie(@RequestParam("movieId") Integer movieId, @RequestParam("userId") Integer userId) {
        usuarioSaveMovieRepository.insertRelation(movieId, userId, "Favorite Movie");
        return "redirect:/movies/";
    }



}
