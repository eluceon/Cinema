package edu.school21.cinema.controllers;

import edu.school21.cinema.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("FilmsAdmin")
@RequestMapping("/films")
public class Films {
    private final MovieService movieService;

    public Films(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String getMoviesPage(Model model) {
        model.addAttribute("movies", movieService.getAll());
        return "/films/all";
    }

    @GetMapping("{id}")
    public String getMovieIfoPage(@PathVariable int id, Model model) {
        model.addAttribute("movie", movieService.get(id));
        return "/films/id";
    }

}
