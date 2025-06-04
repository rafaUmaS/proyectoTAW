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
@RequestMapping("/analist")
public class AnalistController extends BaseController{

    @Autowired protected MovieRepository movieRepository;

    @GetMapping("/movies")
    public String doListarMoviesAnalista(HttpSession session, Model model){
        if(!estaAutenticado(session)) {
            return "redirect:/";
        } else {
            List<EntityMovie> listaMovies = this.movieRepository.findAll();

            model.addAttribute("listaPeliculas", listaMovies);

            return "analistaMovies";
        }
    }

    @GetMapping("/movies/ordenar")
    public String doOrdenarMovies(@RequestParam("filtro") String filtro, Model model){

        List<EntityMovie> listaMovies = this.movieRepository.ordenarMoviesPorFiltro(filtro);

        model.addAttribute("listaPeliculas", listaMovies);

        return "analistaMovies";
    }

    @GetMapping("/volver")
    public String doVolverAnalista(Model model){
        return "redirect:/movies/";
    }
}
