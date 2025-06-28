package es.uma.demospring.myletterbox.controller;

import es.uma.demospring.myletterbox.dao.UsuarioRepository;
import es.uma.demospring.myletterbox.dto.UsuarioDTO;
import es.uma.demospring.myletterbox.entity.EntityUsuario;
import es.uma.demospring.myletterbox.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
 * Autor(es): Rafael Sáez Arana
 */

@Controller
public class AdminController extends BaseController{

    @Autowired
    protected UsuarioService usuarioService;

    @GetMapping("/createUser")
    public String doCreateUser(HttpSession session, Model model){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }else{
            EntityUsuario usuarioActual = (EntityUsuario)session.getAttribute("user");
            if(!usuarioActual.getRol().equals("administrador")) {
                return "redirect:/movies";
            }

            UsuarioDTO usuarioDTO = new UsuarioDTO();
            List<String> roles = UsuarioService.getRolesUsuario();

            model.addAttribute("usuario", usuarioDTO);
            model.addAttribute("roles", roles);
            model.addAttribute("esCrear", true);
            return "editUsuario";
        }
    }
    @PostMapping("/users/")
    public String doCreateUsuario(@Valid @ModelAttribute("usuario") UsuarioDTO usuario,
                                  BindingResult result,
                                  HttpSession session,
                                  Model model){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }else{
            EntityUsuario currentUsuario = (EntityUsuario)session.getAttribute("user");
            if(!currentUsuario.getRol().equals("administrador")) {
                return "redirect:/movies/";
            }
            if(result.hasErrors()){
                List<String> roles = UsuarioService.getRolesUsuario();
                model.addAttribute("usuario", usuario);
                model.addAttribute("roles", roles);
                model.addAttribute("esCrear", true);
                return "editUsuario";
            }
        }

        usuarioService.create(usuario);
        return "redirect:/admin";
    }
    @GetMapping("/admin")
    public String doListarUsuario(HttpSession session, Model model) {

        if (!estaAutenticado(session)) {
            return "redirect:/";
        } else {
            EntityUsuario usuario = (EntityUsuario) session.getAttribute("user");
            if (!usuario.getRol().equals("administrador")) {
                return "redirect:/movies/";
            }
            List<UsuarioDTO> usuarios = usuarioService.getListaUsuarios();
            model.addAttribute("usuarios", usuarios);
            return "admin";
        }
    }

    @GetMapping("/users/delete")
    public String doEliminarUsuario(HttpSession session,  @RequestParam int id){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }else{
            EntityUsuario usuarioActual = (EntityUsuario)session.getAttribute("user");
            if(!usuarioActual.getRol().equals("administrador")) {
                return "redirect:/movies/";
            }
            usuarioService.delete(id);
            return "redirect:/admin";
        }
    }


    @PostMapping("/users/edit")
    public String doEditUsuario(@Valid @ModelAttribute("usuario") UsuarioDTO usuario,
                                BindingResult result,
                                HttpSession session,
                                Model model){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }else{
            EntityUsuario usuarioActual = (EntityUsuario)session.getAttribute("user");
            if(!usuarioActual.getRol().equals("administrador")) {
                return "redirect:/movies/";
            }
            if(result.hasErrors()){
                List<String> roles = UsuarioService.getRolesUsuario();
                model.addAttribute("usuario", usuario);
                model.addAttribute("roles", roles);
                model.addAttribute("esCrear", false);
                return "editUsuario";
            }
            usuarioService.update(usuario);
            return "redirect:/admin";
        }
    }
    @GetMapping("users/edit")
    public String doEdit(HttpSession session, Model model, @RequestParam int id){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }else{
            EntityUsuario usuarioActual = (EntityUsuario)session.getAttribute("user");
            if(!usuarioActual.getRol().equals("administrador")) {
                return "redirect:/movies";
            }

            UsuarioDTO usuario = usuarioService.getUsuarioById(id);
            List<String> roles = UsuarioService.getRolesUsuario();

            model.addAttribute("usuario", usuario);
            model.addAttribute("roles", roles);
            model.addAttribute("esCrear", false);

            return "editUsuario";
        }

    }
}

