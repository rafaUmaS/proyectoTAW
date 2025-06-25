package es.uma.demospring.myletterbox.controller;

import es.uma.demospring.myletterbox.dao.ReviewRepository;
import es.uma.demospring.myletterbox.dao.MovieRepository;
import es.uma.demospring.myletterbox.dao.UsuarioRepository;
import es.uma.demospring.myletterbox.entity.EntityReview;
import es.uma.demospring.myletterbox.entity.EntityMovie;
import es.uma.demospring.myletterbox.entity.EntityUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.Date;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/crear")
    public String crearReview(@RequestParam("movieId") Integer movieId,
                              @RequestParam("userId") Integer userId,
                              @RequestParam("comment") String comment,
                              @RequestParam("rate") Double rate,
                              HttpSession session) {

        EntityUsuario usuario = usuarioRepository.findById(userId).orElse(null);
        EntityMovie movie = movieRepository.findById(movieId).orElse(null);

        if (usuario != null && movie != null) {
            EntityReview review = new EntityReview();
            review.setComment(comment);
            review.setRate(rate);
            review.setCreateTime(new Date());
            review.setUsuarioUserId(usuario);
            review.setMovieMovieId(movie);

            reviewRepository.save(review);
        }

        // Redirige a la misma página de película
        return "redirect:/users/movie?id=" + movieId;
    }
    @PostMapping("/borrar")
    public String borrarReview(HttpSession session, @RequestParam("id") int id){
        this.reviewRepository.deleteById(id);
        return "redirect:/users/user-reviews";
    }
    @PostMapping("/borrarA")
    public String borrarReviewAdmin(HttpSession session,
                                    @RequestParam("id") int id,
                                    @RequestParam("movieId") Integer movieId){
        this.reviewRepository.deleteById(id);
        return "redirect:/users/movie?id=" + movieId;
    }
}
