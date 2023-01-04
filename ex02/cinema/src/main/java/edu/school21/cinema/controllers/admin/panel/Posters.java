package edu.school21.cinema.controllers.admin.panel;

import edu.school21.cinema.models.Movie;
import edu.school21.cinema.models.Poster;
import edu.school21.cinema.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

@Controller
@RequestMapping("/admin/panel/films/poster")
public class Posters {
    private final MovieService movieService;
    private final String storagePath;

    @Autowired
    public Posters(MovieService movieService, String storagePath) {
        this.movieService = movieService;
        this.storagePath = storagePath;
    }

    @GetMapping
    String getPoster(Model model, @RequestParam(value = "id") Integer id) {
        Movie movie = null;
        if (id != null && (movie = movieService.get(id)) != null) {
            model.addAttribute("title", movie.getTitle());
            Poster poster = movie.getPoster();
            String path = null;
            if (poster == null || (path = poster.getPath()) == null) {
                path = storagePath + File.separator + "noimage.jpg";
            }
            model.addAttribute("path", path);
            return "/admin/panel/poster";
        }
        return "redirect:/admin/panel";
    }
}
