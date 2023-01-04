package edu.school21.cinema.models;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class LoginForm {
    @Email
    @NotEmpty(message = "Email is required")
    private String email;
    @Size(min=5, message="Password must be at least 5 characters long")
    private String password;
}
