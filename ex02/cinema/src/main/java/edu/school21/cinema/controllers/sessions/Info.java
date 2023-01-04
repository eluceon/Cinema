package edu.school21.cinema.controllers.sessions;

import edu.school21.cinema.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sessions")
public class Info {
    private final SessionService sessionService;

    @Autowired
    public Info(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("{id}")
    public ModelAndView getSessionInfoPage(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("sessions/info");
        modelAndView.addObject("sessionInfo", sessionService.get(id));
        return modelAndView;
    }
}
