package edu.school21.cinema.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "movie_halls")
public class MovieHall {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "serial_number")
    @NotNull(message="Serial number is required")
    @Min(value = 0, message="Serial number must a positive number")
    @Max(value = 42, message="Max serial number is 42")
    private Integer serialNumber;
    @Column(name = "seats")
    @NotNull(message="Number of seats is required")
    @Min(value = 0, message="Number of seats must a positive number")
    @Max(value = 420, message="Max number of seats is 420")
    private Integer seats;
    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private Admin admin;
    @OneToMany(mappedBy = "movie")
    private List<Session> sessions;
}
