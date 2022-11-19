package edu.school21.cinema.controllers.admin.panel;

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
@RequestMapping("/admin/panel/halls")
class Halls {
    private final MovieHallService movieHallService;

    @Autowired
    public Halls(MovieHallService movieHallService) {
        this.movieHallService = movieHallService;
    }

    @GetMapping
    public String getHalls(Model model) {
        model.addAttribute("movieHalls", movieHallService.getAll());
        return "/admin/panel/halls";
    }

    @PostMapping
    public String addHall(@ModelAttribute @Valid MovieHall movieHall, BindingResult result) {
        System.out.println("TEST FROM POST HALLS");
//        movieHallValidator.validate(movieHall, result);
        if (result.hasErrors()) {
            return "/errorsTMP";
        }
        movieHallService.add(movieHall);
        return "redirect:/admin/panel/halls";
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
