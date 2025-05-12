package es.uma.demospring.myletterbox.controller;

import es.uma.demospring.myletterbox.dao.MovieRepository;
import es.uma.demospring.myletterbox.dao.UsuarioRepository;
import es.uma.demospring.myletterbox.dao.UsuarioSaveMovieRepository;
import es.uma.demospring.myletterbox.entity.EntityMovie;
import es.uma.demospring.myletterbox.entity.EntityUsuario;
import es.uma.demospring.myletterbox.entity.EntityUsuarioSaveMovie;
import es.uma.demospring.myletterbox.ui.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

    @GetMapping("/movies")
    public String doListarMovies(HttpSession session, Model model){

        if(!estaAutenticado(session)){
            return "redirect:/";
        }else {

            List<EntityMovie> listaPeliculas = this.movieRepository.findAll();

            model.addAttribute("listaPeliculas", listaPeliculas);

            return "allMovies";
        }
    }

    @GetMapping("/salir")
    public String doSalir(Model model){
        return "redirect:";
    }
}
