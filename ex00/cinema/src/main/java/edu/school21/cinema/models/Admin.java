package edu.school21.cinema.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull(message = "First name is required")
    @Size(min = 2, max = 30, message = "First name should be between 2 and 30 characters")
    private String firstName;
    @Column(name = "last_name")
    @NotNull(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name should be between 2 and 50 characters")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    @Email
    private String email;
    @Column(name = "password")
    @NotNull(message = "Password is required")
    @Size(min=5, message="Password must be at least 5 characters long")
    private String password;
    @OneToMany(mappedBy = "admin")
    private List<Movie> movies;
    @OneToMany(mappedBy = "admin")
    private List<MovieHall> movieHalls;
    @OneToMany(mappedBy = "admin")
    private List<Poster> posters;
    @OneToMany(mappedBy = "admin")
    private List<Session> sessions;
}
