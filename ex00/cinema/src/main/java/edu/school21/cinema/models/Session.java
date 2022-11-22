package edu.school21.cinema.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "price")
    @Min(value = 0, message = "Price cant' be a negative number")
    private Double price;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "movie_hall_id", referencedColumnName = "id")
    private MovieHall movieHall;
    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private Admin admin;

    public Session(Double price, LocalDateTime dateTime, Movie movie, MovieHall movieHall, Admin admin) {
        this.price = price;
        this.dateTime = dateTime;
        this.movie = movie;
        this.movieHall = movieHall;
        this.admin = admin;
    }
}
