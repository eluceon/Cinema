package edu.school21.cinema.controllers.admin;

import edu.school21.cinema.models.Admin;
import edu.school21.cinema.services.AdminService;
import edu.school21.cinema.services.AuthenticationService;
import edu.school21.cinema.services.AvatarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/profile")
public class Profile {
    private final AvatarService avatarService;
    private final AuthenticationService authenticationService;
    private final AdminService adminService;
    @GetMapping
    public ModelAndView getPage(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("/admin/profile");

        Admin user = (Admin) session.getAttribute("admin");

        modelAndView.addObject( "user", user);
        modelAndView.addObject("avatarHistory", avatarService.getAllByUserId(user.getId()));
        modelAndView.addObject("authHistory", authenticationService.getUserAuthHistory(user.getId()));

        return modelAndView;
    }
}
