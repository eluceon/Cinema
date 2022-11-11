package edu.school21.cinema.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    @NotEmpty
    @NotNull
    private String title;
    @Column(name = "year_of_release")
    @NotNull
    @Min(value = 1900, message = "Year of release can't be less 1900")
    private Integer yearOfRelease;
    @Column(name = "age_restriction")
    @NotNull
    @Min(value = 0, message = "Age can't be less than 0")
    @Max(value = 150, message = "Age can't be more than 150")
    private Integer ageRestriction;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "poster_id", referencedColumnName = "id")
    private Poster posters;
    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private Admin admin;
    @OneToMany(mappedBy = "movie")
    private List<Session> sessions;
}
