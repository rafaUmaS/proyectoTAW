package es.uma.demospring.myletterbox.controller;

import es.uma.demospring.myletterbox.dto.CastDTO;
import es.uma.demospring.myletterbox.service.CastService;
import es.uma.demospring.myletterbox.service.CrewService;
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

    @Autowired private CastService castService;

    @Autowired private CrewService crewService;

    @GetMapping("/")
    public String listarCasts(Model model) {
        model.addAttribute("casts", castService.listarCasts());
        model.addAttribute("crews", crewService.listarCrews());
        return "editor/allCasts";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("casts", castService.listarCasts());
        model.addAttribute("crews", crewService.listarCrews());
        model.addAttribute("cast", new CastDTO());
        return "editor/allCasts";
    }

    @GetMapping("/editar")
    public String mostrarFormularioEditar(@RequestParam("id") Integer id, Model model) {
        CastDTO cast = castService.buscarCastById(id);
        model.addAttribute("cast", cast);
        model.addAttribute("casts", castService.listarCasts());
        model.addAttribute("crews", crewService.listarCrews());
        return "editor/allCasts";
    }

    @PostMapping("/guardar")
    public String guardarCast(@ModelAttribute CastDTO cast) {
        castService.guardarCast(cast);
        return "redirect:/casts/";
    }

    @GetMapping("/eliminar")
    public String eliminarCast(@RequestParam("id") Integer id) {
        this.castService.eliminarCastById(id);
        return "redirect:/casts/";
    }
}
