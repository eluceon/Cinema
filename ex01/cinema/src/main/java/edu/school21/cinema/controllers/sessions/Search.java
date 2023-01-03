package edu.school21.cinema.controllers.sessions;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sessions")
public class Search {
    private final SessionService sessionService;

    @Autowired
    public Search(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public String getPage() {
        return "sessions/search";
    }

    @ResponseBody
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Session>> search(@RequestParam("filmName") String filmName) {
        Map<String, List<Session>> result = new HashMap<>();
        result.put("sessions", sessionService.searchByMovieTitle(filmName));
        return result;
    }
}
