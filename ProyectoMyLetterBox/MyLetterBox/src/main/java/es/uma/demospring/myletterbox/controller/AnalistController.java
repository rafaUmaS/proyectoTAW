package es.uma.demospring.myletterbox.controller;


import es.uma.demospring.myletterbox.dao.MovieRepository;
import es.uma.demospring.myletterbox.entity.EntityMovie;
import es.uma.demospring.myletterbox.service.MovieService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @Autowired protected MovieService movieService;

    @GetMapping("/movies")
    public String doListarMoviesAnalista(HttpSession session, Model model){
        if(!estaAutenticado(session)) {
            return "redirect:/";
        } else {
            List<EntityMovie> listaMovies = this.movieRepository.findAll();

            model.addAttribute("listaPeliculas", listaMovies);
            session.setAttribute("currentFiltro", "");

            return "analistaMovies";
        }
    }



    @GetMapping("/movies/ordenar")
    public String doOrdenarMovies(HttpSession session, @RequestParam("filtro") String filtro, @RequestParam("asc") Integer asc, Model model){
        if(!estaAutenticado(session)){
            return "redirect:/";
        } else {
            if (filtro.equals(session.getAttribute("currentFiltro"))) {
                if (asc == 0) {
                    asc = 1;
                } else {
                    asc = 0;
                }
            } else {
                asc = 0;
            }

            List<EntityMovie> listaMovies = this.movieService.getMoviesOrdenadas(filtro, asc == 0);


            model.addAttribute("asc", asc);
            model.addAttribute("listaPeliculas", listaMovies);
            session.setAttribute("currentFiltro", filtro);

            return "analistaMovies";
        }
    }

    @GetMapping("/volver")
    public String doVolverAnalista(Model model){
        return "redirect:/movies/";
    }

    @GetMapping("/movie")
    public String doPelicula(HttpSession session, @RequestParam("id") Integer id, Model model){
        if(!estaAutenticado(session)) {
            return "redirect:/";
        } else {

            EntityMovie movie = this.movieRepository.findById(id).orElse(null);

            model.addAttribute("movie", movie);

            return "movieAnalist";
        }
    }
}
