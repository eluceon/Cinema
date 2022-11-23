package edu.school21.cinema.util;

import edu.school21.cinema.models.Admin;
import edu.school21.cinema.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SignInValidator implements Validator {
    private final AdminService adminService;

    @Autowired
    public SignInValidator(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Admin.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String email = (String) target;
        if (adminService.findByEmail(email) == null) {
            errors.rejectValue("email", "", "There is no such email");
        }
    }
}
