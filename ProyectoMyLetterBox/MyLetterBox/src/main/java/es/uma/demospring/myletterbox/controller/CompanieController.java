package es.uma.demospring.myletterbox.controller;

import es.uma.demospring.myletterbox.dto.CompanieDTO;
import es.uma.demospring.myletterbox.service.CompaniesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
 * Autor(es): Álvaro Sierra García (100%)
 */

@Controller
@RequestMapping("/companies")
public class CompanieController extends BaseController {

    @Autowired protected CompaniesService companiesService;

    @GetMapping("/")
    public String listarCompanies(HttpSession session, Model model) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }

        model.addAttribute("companies", this.companiesService.listarCompanies());
        return "editor/allCompanies";
    }

    @GetMapping("/nuevo")
    public String crearComapanie(HttpSession session, Model model) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }

        model.addAttribute("companies", this.companiesService.listarCompanies());
        model.addAttribute("companieEditar", new CompanieDTO());
        return "editor/allCompanies";
    }

    @PostMapping("/guardar")
    public String guardarCompanie(@ModelAttribute("companie") CompanieDTO companie, HttpSession session) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }

        this.companiesService.guardarCompanie(companie);
        return "redirect:/companies/";
    }

    @GetMapping("/editar")
    public String editarGenero(@RequestParam("id") Integer id, HttpSession session, Model model) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }

        model.addAttribute("companies", this.companiesService.listarCompanies());
        model.addAttribute("companieEditar", this.companiesService.buscarCompanieById(id));
        return "editor/allCompanies";
    }

    @GetMapping("/eliminar")
    public String eliminarGenero(@RequestParam("id") Integer id, HttpSession session) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }

        this.companiesService.eliminarCompanieById(id);
        return "redirect:/companies/";
    }

}
