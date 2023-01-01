package edu.school21.cinema.controllers.sessions;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/sessions/search")
public class Search {
    private final SessionService sessionService;

    @Autowired
    public Search(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Session>> getPage(@RequestParam("filmName") String filmName) {
        Map<String, List<Session>> result = new HashMap<>();
        result.put("sessions", sessionService.searchByMovieTitle(filmName));
        return result;
    }
}
