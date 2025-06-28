package es.uma.demospring.myletterbox.controller;

import es.uma.demospring.myletterbox.dto.CountryDTO;
import es.uma.demospring.myletterbox.dto.GeneroDTO;
import es.uma.demospring.myletterbox.dto.MovieDTO;
import es.uma.demospring.myletterbox.service.CountriesService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Autor(es): Álvaro Sierra García (100%)
 */
@Controller
@RequestMapping("/countries")
public class CountryController extends BaseController {

    @Autowired protected CountriesService countriesService;

    @GetMapping("/")
    public String listarCountries(Model model, HttpSession session) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }

        model.addAttribute("countries", this.countriesService.listarCountries());
        return "editor/allCountries";
    }

    @GetMapping("/nuevo")
    public String crearCountry(Model model, HttpSession session) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }

        model.addAttribute("countries", this.countriesService.listarCountries());
        model.addAttribute("countryEditar", new CountryDTO());
        model.addAttribute("modoCreacion", true);
        return "editor/allCountries";
    }

    @PostMapping("/guardar")
    public String guardarCountry(@Valid @ModelAttribute("countryEditar") CountryDTO countryDTO,
                                BindingResult result,
                                Model model,
                                 HttpSession session) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }

        boolean modoCreacion = (countryDTO.getIso31661() == null || countryDTO.getIso31661().isEmpty());
        if (result.hasErrors()) {
            model.addAttribute("countries", this.countriesService.listarCountries());
            model.addAttribute("countryEditar", countryDTO);
            model.addAttribute("modoCreacion", modoCreacion);
            return "editor/allCountries";
        }

        this.countriesService.guardarCountry(countryDTO);
        return "redirect:/countries/";
    }

    @GetMapping("/editar")
    public String editarCountry(@RequestParam(value = "id", defaultValue = "-1") String id, Model model, HttpSession session) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }

        model.addAttribute("countries", this.countriesService.listarCountries());
        model.addAttribute("countryEditar", this.countriesService.buscarCountryById(id));
        model.addAttribute("modoCreacion", false);
        return "editor/allCountries";
    }

    @GetMapping("/eliminar")
    public String eliminarCountry(@RequestParam(value = "id", defaultValue = "-1") String id, HttpSession session) {
        if(!estaAutenticado(session)) {
            return "redirect:/";
        }

        this.countriesService.eliminarCountryById(id);
        return "redirect:/countries/";
    }

}
