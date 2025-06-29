package es.uma.demospring.myletterbox.controller;


import es.uma.demospring.myletterbox.dao.MovieRepository;
import es.uma.demospring.myletterbox.dto.CrewDTO;
import es.uma.demospring.myletterbox.dto.MovieDTO;
import es.uma.demospring.myletterbox.dto.PersonaDTO;
import es.uma.demospring.myletterbox.entity.EntityCrew;
import es.uma.demospring.myletterbox.entity.EntityMovie;
import es.uma.demospring.myletterbox.entity.EntityPersona;
import es.uma.demospring.myletterbox.service.CrewService;
import es.uma.demospring.myletterbox.service.MovieService;
import es.uma.demospring.myletterbox.service.PersonaService;
import es.uma.demospring.myletterbox.ui.Filtro;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/*
 * Autor(es): Ivan Pedraza Díez (100%)
 */

@Controller
@RequestMapping("/analist")
public class AnalistController extends BaseController{

    @Autowired protected MovieService movieService;

    @Autowired protected PersonaService personaService;

    @Autowired protected CrewService crewService;

//////// MOVIES //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/movies")
    public String doListarMoviesAnalista(HttpSession session, Model model){
        if(!estaAutenticado(session)) {
            return "redirect:/";
        } else {
            return this.listarMoviesConFiltro(session, null, model);
        }
    }

    @GetMapping("/movies/limpiar-filtro")
    public String doLimpiarFiltro(Session session, Model model){

        return "redirect:/analist/movies";
    }

    public String listarMoviesConFiltro(HttpSession session, Filtro filtro, Model model){

        if(filtro == null) filtro = new Filtro();
        List<MovieDTO> movies = movieService.filtrarYOrdenarPeliculas(filtro, "", true);


        session.setAttribute("filtroAplicado", filtro);
        model.addAttribute("listaPeliculas", movies);
        model.addAttribute("filtro", filtro);
        session.setAttribute("currentCampo", "");
        return "analistaMovies";
    }

    @PostMapping("/movies/filtrar")
    public String doFiltrarMovies(HttpSession session, @ModelAttribute("filtro") Filtro filtro, Model model) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        } else {
            return this.listarMoviesConFiltro(session, filtro, model);
        }
    }


    @GetMapping("/movies/ordenar")
    public String doOrdenarMovies(HttpSession session, @RequestParam("filtro") String campo, @RequestParam("asc") Integer asc, Model model){
        if(!estaAutenticado(session)){
            return "redirect:/";
        } else {

            Filtro filtroActual = (Filtro) session.getAttribute("filtroAplicado");
            if (filtroActual == null) filtroActual = new Filtro();


            String campoActual = (String) session.getAttribute("currentCampo");

            if (campo.equals(campoActual)) {
                asc = (asc == 0) ? 1 : 0;
            } else {
                asc = 0;
            }


            List<MovieDTO> listaMovies = movieService.filtrarYOrdenarPeliculas(filtroActual, campo, asc == 0);

            model.addAttribute("asc", asc);
            model.addAttribute("listaPeliculas", listaMovies);
            model.addAttribute("filtro", filtroActual);
            session.setAttribute("currentCampo", campo);

            return "analistaMovies";
        }
    }

    @GetMapping("/volver")
    public String doVolverAnalista(HttpSession session){
        if(!estaAutenticado(session)){
            return "redirect:/";
        } else {
            return "redirect:/movies/";
        }
    }

    @GetMapping("/movie")
    public String doPelicula(HttpSession session, @RequestParam("id") Integer id, Model model){
        if(!estaAutenticado(session)) {
            return "redirect:/";
        } else {

            MovieDTO movie = this.movieService.buscarMovieById(id);

            List<CrewDTO> crewDTOList = this.crewService.listarCrewById(movie.getCrewList());

            Map<Integer, PersonaDTO> personaDTOMap = new HashMap<>();

            for(CrewDTO crewDTO : crewDTOList){
                personaDTOMap.put(crewDTO.getPERSONAid(), this.personaService.getPersonaById(crewDTO.getPERSONAid()));;
            }

            model.addAttribute("movie", movie);
            model.addAttribute("crewList", crewDTOList);
            model.addAttribute("personaMap", personaDTOMap);

            return "movieAnalist";
        }
    }


//////// PERSONAS ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/personas")
    public String doListarPersonasAnalista(HttpSession session, Model model){
        if(!estaAutenticado(session)) {
            return "redirect:/";
        } else {
            return this.listarPersonasConFiltro(session, null, model);
        }
    }

    public String listarPersonasConFiltro(HttpSession session, Filtro filtro, Model model){

        List<PersonaDTO> personas;

        if(filtro==null){
            filtro = new Filtro();
            personas = this.personaService.listarPersonas();
        }else {
            personas = this.personaService.listarPersonas(filtro.getNombre());
        }

        session.setAttribute("filtro", filtro);
        model.addAttribute("personas", personas);
        model.addAttribute("filtro", filtro);
        session.setAttribute("currentCampo", "");
        return "analistaPersonas";
    }

    @PostMapping("/personas/filtrar")
    public String doFiltrarPersonas(HttpSession session, @ModelAttribute("filtro") Filtro filtro, Model model) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        } else {
            session.setAttribute("filtroAplicado", filtro);
            return this.listarPersonasConFiltro(session, filtro, model);
        }
    }

    @GetMapping("/personas/ordenar")
    public String doOrdenarPersonas(HttpSession session, @RequestParam("filtro") String campo, @RequestParam("asc") Integer asc, Model model){
        if(!estaAutenticado(session)){
            return "redirect:/";
        } else {

            Filtro filtroActual = (Filtro) session.getAttribute("filtroAplicado");
            String nombreFiltro = (filtroActual!=null) ? filtroActual.getNombre() : null;


            String campoActual = (String) session.getAttribute("currentCampo");

            if (campo.equals(campoActual)) {
                asc = (asc == 0) ? 1 : 0;
            } else {
                asc = 0;
            }

            List<PersonaDTO> personas = this.personaService.getPersonasOrdenadas(campo, asc == 0, nombreFiltro);


            model.addAttribute("asc", asc);
            model.addAttribute("personas", personas);
            model.addAttribute("filtro", filtroActual != null ? filtroActual : new Filtro());
            session.setAttribute("currentCampo", campo);

            return "analistaPersonas";
        }
    }

    @GetMapping("/persona/selectedCrew")
    public String doPersonaSelectedCrew(HttpSession session,@ModelAttribute("id") Integer id, @ModelAttribute("crewId") Integer crewId, Model model){
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }else {
            PersonaDTO persona = this.personaService.getPersonaById(id);

            CrewDTO crewSelected =  this.crewService.getCrewById(crewId);

            List<CrewDTO> crewDTOList = this.crewService.listarCrewById(persona.getCrewList());

            Map<Integer, MovieDTO> movieDTOMap = new HashMap<>();

            for (CrewDTO crewDTO : crewDTOList){
                movieDTOMap.put(crewDTO.getMovieId(), this.movieService.getMovieDTOById(crewDTO.getMovieId()));
            }

            model.addAttribute("movieMap", movieDTOMap);
            model.addAttribute("crewList", crewDTOList);
            model.addAttribute("crewSelected", crewSelected);
            model.addAttribute("persona", persona);


            return "personaAnalist";
        }
    }

    @GetMapping("/persona")
    public String doPersona(HttpSession session,@RequestParam("id") Integer id, Model model){
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }else {
            PersonaDTO persona = this.personaService.getPersonaById(id);

            List<CrewDTO> crewDTOList = this.crewService.listarCrewById(persona.getCrewList());

            Map<Integer, MovieDTO> movieDTOMap = new HashMap<>();

            for (CrewDTO crewDTO : crewDTOList){
                movieDTOMap.put(crewDTO.getMovieId(), this.movieService.getMovieDTOById(crewDTO.getMovieId()));
            }

            model.addAttribute("movieMap", movieDTOMap);
            model.addAttribute("crewList", crewDTOList);
            model.addAttribute("crewSelected", null);
            model.addAttribute("persona", persona);


            return "personaAnalist";
        }
    }
}
