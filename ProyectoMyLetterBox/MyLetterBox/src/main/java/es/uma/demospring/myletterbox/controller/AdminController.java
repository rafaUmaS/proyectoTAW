package es.uma.demospring.myletterbox.controller;

import es.uma.demospring.myletterbox.dao.UsuarioRepository;
import es.uma.demospring.myletterbox.entity.EntityUsuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Autor(es):
 */

@Controller
public class AdminController extends BaseController{

    @Autowired
    protected UsuarioRepository usuarioRepository;



    @GetMapping("/createUser")
    public String doCreateUser(HttpSession session){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }else{
            EntityUsuario usuarioActual = (EntityUsuario)session.getAttribute("user");
            if(!usuarioActual.getRol().equals("administrador")) {
                return "redirect:/movies";
            }
            return "createUser";
        }
    }
    @PostMapping("/users/")
    public String doCreateUsuario(HttpSession session, @ModelAttribute EntityUsuario usuarioToCreate){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }else{
            EntityUsuario currentUsuario = (EntityUsuario)session.getAttribute("user");
            if(!currentUsuario.getRol().equals("administrador")) {
                return "redirect:/movies/";
            }
        }

        usuarioRepository.save(usuarioToCreate);
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
            List<EntityUsuario> usuarios = usuarioRepository.findAll();
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
            EntityUsuario  usuario = usuarioRepository.findById(id).orElse(null);
            usuarioRepository.delete(usuario);
            return "redirect:/admin";
        }
    }


    @PostMapping("/users/edit")
    public String doEditUsuario(HttpSession session, @ModelAttribute EntityUsuario usuario){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }else{
            EntityUsuario usuarioActual = (EntityUsuario)session.getAttribute("user");
            if(!usuarioActual.getRol().equals("administrador")) {
                return "redirect:/movies/";
            }
            usuarioRepository.save(usuario);
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
            EntityUsuario usuario = (EntityUsuario)usuarioRepository.findById(id).orElse(null);
            model.addAttribute("usuario", usuario);
            return "editUsuario";
        }


    }
}

