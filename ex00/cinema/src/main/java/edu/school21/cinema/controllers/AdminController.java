package edu.school21.cinema.controllers;

import edu.school21.cinema.models.MovieHall;
import edu.school21.cinema.services.AdminService;
import edu.school21.cinema.services.MovieHallService;
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
@RequestMapping("/admin/panel")
public class AdminController {
    private final AdminService adminService;
    private final MovieHallService movieHallService;
    private final MovieService movieService;

    @Autowired
    public AdminController(AdminService adminService, MovieHallService movieHallService, MovieService movieService) {
        this.adminService = adminService;
        this.movieHallService = movieHallService;
        this.movieService = movieService;
    }

    @GetMapping("/halls")
    public String getHalls(Model model) {
        model.addAttribute("movieHalls", movieHallService.getAll());
        return "/admin/panel/halls";
    }

    @PostMapping("/halls")
    public String addHall(@ModelAttribute @Valid MovieHall movieHall, BindingResult result) {
        System.out.println("TEST FROM POST HALLS");
//        movieHallValidator.validate(movieHall, result);
        if (result.hasErrors()) {
            return "/errorsTMP";
        }
        movieHallService.add(movieHall);
        return "redirect:/admin/panel/halls";
    }

    @GetMapping("/films")
    public String getMovies(Model model) {
        model.addAttribute("movies", movieService.getAll());
        return "/admin/panel/films";
    }

    @PostMapping("/films")
    public String postMovie(Model model) {
        model.addAttribute("movies", movieService.getAll());
        return "redirect:/admin/panel/films";
    }

    @GetMapping("/sessions")
    private String getSessions() {
        return "/admin/panel/sessions";
    }

    @PostMapping("/sessions")
    private String postSession() {
        return "redirect:/admin/panel/sessions";
    }
}
