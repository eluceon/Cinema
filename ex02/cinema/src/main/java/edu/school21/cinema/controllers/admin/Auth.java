package edu.school21.cinema.controllers.admin;

import edu.school21.cinema.models.Admin;
import edu.school21.cinema.models.LoginForm;
import edu.school21.cinema.services.AdminService;
import edu.school21.cinema.util.SignInValidator;
import edu.school21.cinema.util.SignUpValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class Auth {
    private final AdminService adminService;
    private final SignInValidator signInValidator;
    private final SignUpValidator signUpValidator;

    @Autowired
    public Auth(AdminService adminService, SignInValidator signInValidator, SignUpValidator signUpValidator) {
        this.adminService = adminService;
        this.signInValidator = signInValidator;
        this.signUpValidator = signUpValidator;
    }

    @GetMapping("/signin")
    public String getSignIn(Model model)  {
        model.addAttribute("loginForm", new LoginForm());
        return "/admin/auth/signin";
    }

    @PostMapping("/signin")
    public String signIn(@ModelAttribute @Valid LoginForm loginForm, BindingResult result, HttpSession session) {
        signInValidator.validate(loginForm.getEmail(), result);
        if (result.hasErrors()) {
            return "/admin/auth/signin";
        }
        Admin admin = adminService.signIn(loginForm.getEmail(), loginForm.getPassword());
        if (admin == null) {
            result.rejectValue("email", "", "Wrong email or password");
            return "/admin/auth/signin";
        }

        session.setAttribute("admin", admin);
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
