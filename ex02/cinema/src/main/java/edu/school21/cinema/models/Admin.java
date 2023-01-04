package edu.school21.cinema.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    @Size(min = 2, max = 30, message = "First name should be between 2 and 30 characters")
    private String firstName;
    @Column(name = "last_name")
    @Size(min = 2, max = 50, message = "Last name should be between 2 and 50 characters")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    @Email
    @NotEmpty(message = "email is required")
    private String email;
    @Column(name = "password")
    @Size(min=5, message="Password must be at least 5 characters long")
    private String password;
}
