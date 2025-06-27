package es.uma.demospring.myletterbox.controller;

import es.uma.demospring.myletterbox.dto.CrewDTO;
import es.uma.demospring.myletterbox.service.CastService;
import es.uma.demospring.myletterbox.service.CrewService;
import es.uma.demospring.myletterbox.service.MovieService;
import es.uma.demospring.myletterbox.service.PersonaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
 * Autor(es): Álvaro Sierra García (100%)
 */

@Controller
@RequestMapping("/crews")
public class CrewController extends BaseController{

    @Autowired protected CastService castService;

    @Autowired protected CrewService crewService;

    @Autowired protected PersonaService personaService;

    @Autowired protected MovieService movieService;

    @GetMapping("/")
    public String listarCrews(Model model, HttpSession session) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }

        model.addAttribute("crews", crewService.listarCrews());
        model.addAttribute("personas", personaService.listarPersonas());
        model.addAttribute("movies", movieService.listarMovies());
        model.addAttribute("casts", castService.listarCasts());
        return "editor/allCrews";
    }

    @GetMapping("/nuevo")
    public String crearCrew(Model model, HttpSession session) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }

        model.addAttribute("crews", crewService.listarCrews());
        model.addAttribute("personas", personaService.listarPersonas());
        model.addAttribute("movies", movieService.listarMovies());
        model.addAttribute("casts", castService.listarCasts());
        model.addAttribute("crewEditar", new CrewDTO());
        return "editor/allCrews";
    }

    @GetMapping("/editar")
    public String editarCrew(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model, HttpSession session) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }

        CrewDTO crew = crewService.buscarCrewById(id);
        model.addAttribute("crews", crewService.listarCrews());
        model.addAttribute("personas", personaService.listarPersonas());
        model.addAttribute("movies", movieService.listarMovies());
        model.addAttribute("casts", castService.listarCasts());
        model.addAttribute("crewEditar", crew);
        return "editor/allCrews";
    }

    @PostMapping("/guardar")
    public String guardarCast(@ModelAttribute("crewEditar") CrewDTO crewDTO, HttpSession session) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }

        crewService.guardarCrew(crewDTO);
        return "redirect:/crews/";
    }

    @GetMapping("/eliminar")
    public String eliminarCast(@RequestParam(value = "id", defaultValue = "-1") Integer id, HttpSession session) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }

        this.crewService.eliminarCrewById(id);
        return "redirect:/crews/";
    }
}