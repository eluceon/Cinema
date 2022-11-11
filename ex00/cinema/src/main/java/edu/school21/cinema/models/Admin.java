package edu.school21.cinema.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
    @NotNull
    @Size(min=2, message="First name must be at least 2 characters long")
    private String firstName;
    @Column(name = "last_name")
    @NotNull
    @Size(min=2, message="Last name must be at least 2 characters long")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    @NotNull
    @Email
    private String email;
    @Column(name = "password")
    @NotNull
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
