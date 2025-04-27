package es.uma.demospring.myletterbox.controller;

import es.uma.demospring.myletterbox.dao.UsuarioRepository;
import es.uma.demospring.myletterbox.entity.EntityUsuario;
import es.uma.demospring.myletterbox.ui.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @GetMapping("/listar")
    public String doListar (Model model) {

        List<EntityUsuario> usuarios = usuarioRepository.findAll();

        model.addAttribute("usuarios", usuarios);

        return "listar";
    }

    @GetMapping("/")
    public String doLogin(Model model) {
        return "login";
    }

    @PostMapping("/autentica")
    public String doAutentica(@ModelAttribute() Usuario usuario, Model model, HttpSession session){

        EntityUsuario user = this.usuarioRepository.autenticaUsuario(usuario.getUsername(),
                                                                        usuario.getPassword());

        if(user==null){
            return "login";
        }else {
            session.setAttribute("user", user);
            return "redirect:/users/movies";
        }
    }

}
