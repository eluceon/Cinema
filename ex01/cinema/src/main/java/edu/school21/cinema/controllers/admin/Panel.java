package edu.school21.cinema.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin/panel")
public class Panel {
    @GetMapping()
    public String getPage() {
        return "/admin/panel/index";
    }
}
