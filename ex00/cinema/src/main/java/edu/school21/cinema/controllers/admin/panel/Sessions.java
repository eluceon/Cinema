package edu.school21.cinema.controllers.admin.panel;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/panel/sessions")
class Sessions {
    private final SessionService sessionService;

    @Autowired
    Sessions(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public String getSessions(Model model) {
        model.addAttribute("sessions", sessionService.getAll());
        return "/admin/panel/sessions";
    }

    @PostMapping
    public String addSession(@ModelAttribute @Valid Session session, BindingResult result) {
        if (result.hasErrors()) {
            return "errorTMP";
        }
        sessionService.add(session);
        return "redirect:/admin/panel/sessions";
    }
}
