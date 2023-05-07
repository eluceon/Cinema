package edu.school21.cinema.controllers.admin.panel;

import edu.school21.cinema.models.MovieHall;
import edu.school21.cinema.services.MovieHallService;
import edu.school21.cinema.util.MovieHallValidator;
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
@RequestMapping("/admin/panel/halls")
class Halls {
    private final MovieHallService movieHallService;
    private final MovieHallValidator movieHallValidator;

    @Autowired
    public Halls(MovieHallService movieHallService, MovieHallValidator movieHallValidator) {
        this.movieHallService = movieHallService;
        this.movieHallValidator = movieHallValidator;
    }

    @GetMapping
    public String getHalls(Model model) {
        model.addAttribute("movieHalls", movieHallService.getAll());
        model.addAttribute("movieHall", new MovieHall());
        return "/admin/panel/halls";
    }

    @PostMapping
    public String addHall(@ModelAttribute @Valid MovieHall movieHall, BindingResult result) {
        movieHallValidator.validate(movieHall, result);
        if (result.hasErrors()) {
            return "/admin/panel/halls";
        }
        movieHallService.add(movieHall);
        return "redirect:/admin/panel/halls";
    }
}
