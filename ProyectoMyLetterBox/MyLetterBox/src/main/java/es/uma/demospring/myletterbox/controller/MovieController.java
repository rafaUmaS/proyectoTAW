package es.uma.demospring.myletterbox.controller;

import es.uma.demospring.myletterbox.dao.GenreRepository;
import es.uma.demospring.myletterbox.dao.MovieRepository;
import es.uma.demospring.myletterbox.dao.UsuarioSaveMovieRepository;
import es.uma.demospring.myletterbox.dao.ReviewRepository;
import es.uma.demospring.myletterbox.entity.EntityGenre;
import es.uma.demospring.myletterbox.entity.EntityMovie;
import es.uma.demospring.myletterbox.entity.EntityUsuario;
import es.uma.demospring.myletterbox.entity.EntityReview;
import es.uma.demospring.myletterbox.entity.EntityUsuarioSaveMovie;
import es.uma.demospring.myletterbox.ui.Movie;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
 * Autor(es):
 */

@Controller
@RequestMapping("/movies")
public class MovieController extends BaseController {

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
    public String crearMovie(Model model){
        List<EntityGenre> generos = this.genreRepository.findAll();
        Movie pelicula = new Movie();
        model.addAttribute("generos", generos);
        model.addAttribute("esCrear", true);
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("estados", Movie.Estado.values());
        return "editMovie";
    }

    @PostMapping("/borrar")
    public String borrarMovie(HttpSession session, @RequestParam("id") int id){
        this.movieRepository.deleteById(id);
        return "redirect:/movies/";
    }

    @GetMapping("/editar")
    public String doEditar(@RequestParam("id") Integer id, Model model){
        EntityMovie movie = this.movieRepository.findById(id).orElse(null);
        List<EntityGenre> generos = this.genreRepository.findAll();

        Movie pelicula = new Movie();
        pelicula.setId(movie.getMovieId());
        pelicula.setTitulo(movie.getTitle());
        pelicula.setTituloOriginal(movie.getOriginalTitle());
        pelicula.setIdioma(movie.getOriginalLanguage());
        pelicula.setDescripcion(movie.getOverview());
        pelicula.setPopularidad(movie.getPopularity());
        pelicula.setBeneficios(movie.getRevenue());
        pelicula.setPresupuesto(movie.getBudget());
        pelicula.setDuracion(movie.getRuntime());
        pelicula.setNumVotos(movie.getVoteCount());
        pelicula.setMediaVotos(movie.getVoteAverage());
        pelicula.setFecha(movie.getReleaseDate());
        pelicula.setEstado(movie.getStatus());
        List<Integer> genresId = new ArrayList<Integer>();
        for(EntityGenre genre : movie.getGenreList()){
            genresId.add(genre.getId());
        }
        pelicula.setGeneros(genresId);

        model.addAttribute("pelicula", pelicula);
        model.addAttribute("esCrear", false);
        model.addAttribute("generos", generos);
        model.addAttribute("estados", Movie.Estado.values());

        return "editMovie";
    }

    @PostMapping("/guardar")
    public String doGuardar(Model model, @ModelAttribute("pelicula") Movie pelicula, HttpSession session){
        EntityMovie movie = this.movieRepository.findById(pelicula.getId()).orElse(null);
        if(movie == null){
            movie = new EntityMovie();
        }

        movie.setTitle(pelicula.getTitulo());
        movie.setName(pelicula.getTitulo());
        movie.setOriginalTitle(pelicula.getTituloOriginal());
        movie.setOriginalLanguage(pelicula.getIdioma());
        movie.setOverview(pelicula.getDescripcion());
        movie.setPopularity(pelicula.getPopularidad());
        movie.setRevenue(pelicula.getBeneficios());
        movie.setBudget(pelicula.getPresupuesto());
        movie.setRuntime(pelicula.getDuracion());
        movie.setVoteCount(pelicula.getNumVotos());
        movie.setVoteAverage(pelicula.getMediaVotos());
        movie.setReleaseDate(pelicula.getFecha());
        movie.setStatus(pelicula.getEstado());

        if(pelicula.getGeneros() != null){
            List<EntityGenre> generos = this.genreRepository.findAllById(pelicula.getGeneros());
            movie.setGenreList(generos);
        }

        this.movieRepository.save(movie);
        return "redirect:/movies/";
    }

    @PostMapping("/like")
    public String doLikeMovie(@RequestParam("movieId") Integer movieId, @RequestParam("userId") Integer userId) {
        usuarioSaveMovieRepository.insertRelation(movieId, userId, "Favorite Movie");
        return "redirect:/movies/";
    }



}
