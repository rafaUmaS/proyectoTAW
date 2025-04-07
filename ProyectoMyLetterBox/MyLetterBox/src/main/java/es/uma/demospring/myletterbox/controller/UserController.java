package es.uma.demospring.myletterbox.controller;

import es.uma.demospring.myletterbox.dao.UsuarioRepository;
import es.uma.demospring.myletterbox.entity.EntityUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String doListar (Model model) {

        List<EntityUsuario> usuarios = usuarioRepository.findAll();

        model.addAttribute("usuarios", usuarios);

        return "listar";
    }

}
