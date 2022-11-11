package edu.school21.cinema.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/panel/halls")
    public String processMovieHall() {
        return "admin/panel/halls";
    }

    @GetMapping("/panel/films")
    public String processFilm() {
        return "admin/panel/films";
    }

    @GetMapping("/panel/sessions")
    private String processSessions() {
        return "admin/panel/sessions";
    }
}
