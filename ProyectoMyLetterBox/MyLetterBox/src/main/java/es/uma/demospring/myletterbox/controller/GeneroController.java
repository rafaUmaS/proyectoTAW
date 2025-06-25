package es.uma.demospring.myletterbox.controller;

import es.uma.demospring.myletterbox.dto.GeneroDTO;
import es.uma.demospring.myletterbox.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
 * Autor(es): Álvaro Sierra García (100%)
 */

@Controller
@RequestMapping("/generos")
public class GeneroController extends BaseController{

    @Autowired private GeneroService generoService;

    @GetMapping("/")
    public String listarGeneros(Model model) {
        model.addAttribute("generos", this.generoService.listarGeneros());
        return "editor/allGeneros";
    }

    @GetMapping("/nuevo")
    public String crearGenero(Model model) {
        model.addAttribute("generos", this.generoService.listarGeneros());
        model.addAttribute("generoEditar", new GeneroDTO());
        return "editor/allGeneros";
    }

    @PostMapping("/guardar")
    public String guardarGenero(@ModelAttribute("genero") GeneroDTO genero) {
        this.generoService.guardarGenero(genero);
        return "redirect:/generos/";
    }

    @GetMapping("/editar")
    public String editarGenero(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("generos", this.generoService.listarGeneros());
        model.addAttribute("generoEditar", this.generoService.buscarGeneroById(id));
        return "editor/allGeneros";
    }

    @GetMapping("/eliminar")
    public String eliminarGenero(@RequestParam("id") Integer id) {
        this.generoService.eliminarGeneroById(id);
        return "redirect:/generos/";
    }
}
