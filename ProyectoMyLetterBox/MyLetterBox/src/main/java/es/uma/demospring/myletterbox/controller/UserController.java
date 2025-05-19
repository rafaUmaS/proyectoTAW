package es.uma.demospring.myletterbox.controller;

import es.uma.demospring.myletterbox.dao.MovieRepository;
import es.uma.demospring.myletterbox.dao.UsuarioRepository;
import es.uma.demospring.myletterbox.dao.UsuarioSaveMovieRepository;
import es.uma.demospring.myletterbox.entity.EntityMovie;
import es.uma.demospring.myletterbox.entity.EntityUsuario;
import es.uma.demospring.myletterbox.entity.EntityUsuarioSaveMovie;
import es.uma.demospring.myletterbox.ui.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {


    @Autowired protected UsuarioRepository usuarioRepository;
    @Autowired protected UsuarioSaveMovieRepository usuarioSaveMovieRepository;



    @GetMapping("/saved-movies")
    public String doListarSavedMovieUsuario(HttpSession session, Model model){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }else {

            int usuarioID = ((EntityUsuario)session.getAttribute("user")).getUserId();
            List<EntityUsuarioSaveMovie> savedMovies = this.usuarioSaveMovieRepository.listaUsuarioSaveMovie(
                    usuarioID);

            model.addAttribute("savedMovies", savedMovies);

            return "usuarioMovies";
        }
    }
    @GetMapping("/listar")
    public String doListarUsuario(HttpSession session, Model model){

        if(!estaAutenticado(session)){
            return "redirect:/";
        }else{
            EntityUsuario usuario = (EntityUsuario)session.getAttribute("user");
            if(!usuario.getRol().equals("administrador")) {
                return "redirect:/movies/";
            }
        }
        List<EntityUsuario> usuarios = usuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "listar";
    }


    @GetMapping("/movie")
    public String doMostrarMovie(HttpSession session,Integer id, Model model){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }else {
            EntityMovie movie =  this.movieRepository.findById(id).orElse(null);
            model.addAttribute("movie", movie);
            return "movie";
        }
    }


    @GetMapping("/salir")
    public String doSalir(Model model){
        return "redirect:";
    }




    @GetMapping("/edit")
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
    @PostMapping("/edit")
    public String doEditUsuario(HttpSession session, @ModelAttribute EntityUsuario usuario){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }else{
            EntityUsuario usuarioActual = (EntityUsuario)session.getAttribute("user");
            if(!usuarioActual.getRol().equals("administrador")) {
                return "redirect:/movies/";
            }
            usuarioRepository.save(usuario);
            return "redirect:/users/listar";
        }
    }
    @GetMapping("/delete")
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
            return "redirect:/users/listar";
        }
    }

}
