package edu.school21.cinema.controllers.admin;

import edu.school21.cinema.models.Admin;
import edu.school21.cinema.models.Authentication;
import edu.school21.cinema.models.LoginForm;
import edu.school21.cinema.services.AdminService;
import edu.school21.cinema.services.AuthenticationService;
import edu.school21.cinema.util.SignInValidator;
import edu.school21.cinema.util.SignUpValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class Auth {
    private final AdminService adminService;
    private final SignInValidator signInValidator;
    private final SignUpValidator signUpValidator;
    private final AuthenticationService authenticationService;

    @GetMapping("/signin")
    public String getSignIn(Model model)  {
        model.addAttribute("loginForm", new LoginForm());
        return "/admin/auth/signin";
    }

    @PostMapping("/signin")
    public String signIn(@ModelAttribute @Valid LoginForm loginForm, BindingResult result, HttpServletRequest req) {
        signInValidator.validate(loginForm.getEmail(), result);
        if (result.hasErrors()) {
            return "/admin/auth/signin";
        }
        Admin admin = adminService.signIn(loginForm.getEmail(), loginForm.getPassword());
        if (admin == null) {
            result.rejectValue("email", "", "Wrong email or password");
            return "/admin/auth/signin";
        }

        req.getSession().setAttribute("admin", admin);
        authenticationService.add(new Authentication(req.getRemoteAddr(), LocalDateTime.now(), admin));
        return "redirect:/admin/panel";
    }

    @GetMapping("/signup")
    public String getSignUp(Model model) {
        model.addAttribute("admin", new Admin());
        return "/admin/auth/signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute @Valid Admin admin, BindingResult result) {
        signUpValidator.validate(admin, result);
        if (result.hasErrors()) {
            return "/admin/auth/signup";
        }
        adminService.add(admin);
        return "redirect:/admin/signin";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/admin/signin";
    }
}
