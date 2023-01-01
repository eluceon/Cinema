package edu.school21.cinema.controllers.admin.panel;

import edu.school21.cinema.models.Admin;
import edu.school21.cinema.models.Movie;
import edu.school21.cinema.models.MovieHall;
import edu.school21.cinema.models.Session;
import edu.school21.cinema.services.MovieHallService;
import edu.school21.cinema.services.MovieService;
import edu.school21.cinema.services.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin/panel/sessions")
public class Sessions {
    private final SessionService sessionService;
    private final MovieHallService movieHallService;
    private final MovieService movieService;

    public Sessions(SessionService sessionService, MovieHallService movieHallService, MovieService movieService) {
        this.sessionService = sessionService;
        this.movieHallService = movieHallService;
        this.movieService = movieService;
    }

    @GetMapping
    public ModelAndView getPage() {
        ModelAndView modelAndView = new ModelAndView("/admin/panel/sessions");
        List<Session> sessions= sessionService.getAll();
        if (sessions.size() > 0) {
            modelAndView.addObject("sessions", sessions);
        }
        modelAndView.addObject("movies", movieService.getAll());
        modelAndView.addObject("movieHalls", movieHallService.getAll());
        return modelAndView;
    }

    @PostMapping
    public String postPage(HttpSession session,
                           @RequestParam("movie") Integer movie_id,
                           @RequestParam("hall") Integer hall_id,
                           @RequestParam("dateTime") String dateTime,
                           @RequestParam("price") Double price
    ) {
        Admin admin = (Admin) session.getAttribute("admin");
        MovieHall movieHall = movieHallService.get(hall_id);
        Movie movie = movieService.get(movie_id);
        if (movie == null || movieHall == null){
            return  "/admin/panel/sessions";
        }
        sessionService.add(new Session(price, LocalDateTime.parse(dateTime), movie, movieHall, admin));
        return  "redirect:/admin/panel/sessions";
    }
}
