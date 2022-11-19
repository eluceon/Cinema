package edu.school21.cinema.controllers.admin.panel;

import edu.school21.cinema.models.Movie;
import edu.school21.cinema.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/panel/films")
class Films {
    private final MovieService movieService;

    @Autowired
    public Films(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String getMovies(Model model) {
        model.addAttribute("movies", movieService.getAll());
        return "/admin/panel/films";
    }

    @PostMapping
    public String addMovie(@ModelAttribute @Valid Movie movie, BindingResult result) {
        System.out.println("TEST FROM POST HALLS");
//        movieHallValidator.validate(movieHall, result);
        if (result.hasErrors()) {
            return "/errorsTMP";
        }
        movieService.add(movie);
        return "redirect:/admin/panel/films";
    }

}
