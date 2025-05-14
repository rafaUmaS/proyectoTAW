package es.uma.demospring.myletterbox.controller;

import es.uma.demospring.myletterbox.dao.MovieRepository;
import es.uma.demospring.myletterbox.entity.EntityMovie;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController extends BaseController {
    @Autowired
    protected MovieRepository movieRepository;

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
}
