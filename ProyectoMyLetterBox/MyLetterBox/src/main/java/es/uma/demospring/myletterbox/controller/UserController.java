package es.uma.demospring.myletterbox.controller;

import es.uma.demospring.myletterbox.dao.UsuarioRepository;
import es.uma.demospring.myletterbox.dao.UsuarioSaveMovieRepository;
import es.uma.demospring.myletterbox.entity.EntityUsuario;
import es.uma.demospring.myletterbox.entity.EntityUsuarioSaveMovie;
import es.uma.demospring.myletterbox.ui.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {


    @Autowired protected UsuarioRepository usuarioRepository;
    @Autowired protected UsuarioSaveMovieRepository usuarioSaveMovieRepository;


    @GetMapping("/movies")
    public String doListarMovieUsuario(HttpSession session, Model model){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }else {

            int usuarioID = ((EntityUsuario)session.getAttribute("user")).getId();
            List<EntityUsuarioSaveMovie> savedMovies = this.usuarioSaveMovieRepository.listaUsuarioSaveMovie(
                    usuarioID);

            model.addAttribute("savedMovies", savedMovies);

            return "usuarioMovies";
        }
    }
}
