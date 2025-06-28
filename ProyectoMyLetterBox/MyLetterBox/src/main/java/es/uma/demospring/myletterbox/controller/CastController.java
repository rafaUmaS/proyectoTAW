package es.uma.demospring.myletterbox.controller;

import es.uma.demospring.myletterbox.dto.CastDTO;
import es.uma.demospring.myletterbox.service.CastService;
import es.uma.demospring.myletterbox.service.CrewService;
import es.uma.demospring.myletterbox.service.MovieService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
 * Autor(es): Álvaro Sierra García (100%)
 */

@Controller
@RequestMapping("/casts")
public class CastController extends BaseController{

    @Autowired protected CastService castService;

    @Autowired protected CrewService crewService;

    @Autowired protected MovieService movieService;

    @GetMapping("/")
    public String listarCasts(Model model, HttpSession session) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }

        model.addAttribute("casts", castService.listarCasts());
        model.addAttribute("crews", crewService.listarCrews());
        model.addAttribute("movies", movieService.listarMovies());
        return "editor/allCasts";
    }

    @GetMapping("/nuevo")
    public String crearCast(Model model, HttpSession session) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }

        model.addAttribute("casts", castService.listarCasts());
        model.addAttribute("crews", crewService.listarCrews());
        model.addAttribute("movies", movieService.listarMovies());
        model.addAttribute("castEditar", new CastDTO());
        return "editor/allCasts";
    }

    @GetMapping("/editar")
    public String editarCast(@RequestParam("id") Integer id, Model model, HttpSession session) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }

        CastDTO cast = castService.buscarCastById(id);
        model.addAttribute("castEditar", cast);
        model.addAttribute("casts", castService.listarCasts());
        model.addAttribute("crews", crewService.listarCrews());
        model.addAttribute("movies", movieService.listarMovies());
        return "editor/allCasts";
    }

    @PostMapping("/guardar")
    public String guardarCast(@ModelAttribute("castEditar") CastDTO cast, HttpSession session) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }

        castService.guardarCast(cast);
        return "redirect:/casts/";
    }

    @GetMapping("/eliminar")
    public String eliminarCast(@RequestParam(value = "id", defaultValue = "-1") Integer id, HttpSession session) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }

        this.castService.eliminarCastById(id);
        return "redirect:/casts/";
    }
}
