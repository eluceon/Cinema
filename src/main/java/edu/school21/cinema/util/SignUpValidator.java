package edu.school21.cinema.util;

import edu.school21.cinema.models.Admin;
import edu.school21.cinema.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class SignUpValidator implements Validator {
    private final AdminService adminService;

    @Autowired
    public SignUpValidator(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Admin.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Admin admin = (Admin) target;
        if (adminService.findByEmail(admin.getEmail()) != null) {
            errors.rejectValue("email", "", "This email is already in use");
        }
    }
}
