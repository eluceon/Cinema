package edu.school21.cinema.controllers;

import edu.school21.cinema.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("FilmsAdmin")
@RequestMapping("/films")
public class Films {
    private final MovieService movieService;

    public Films(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String getMovies(Model model) {
        model.addAttribute("movies", movieService.getAll());
        return "/films/all";
    }

}
