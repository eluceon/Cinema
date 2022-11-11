package edu.school21.cinema.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotNull
    @NotEmpty
    @Min(value = 0, message="Serial number must a positive number")
    private Integer serialNumber;
    @Column(name = "seats")
    @NotNull
    @NotEmpty
    @Min(value = 0, message="Number of seats must a positive number")
    private Integer seats;
    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private Admin admin;
    @OneToMany(mappedBy = "movie")
    private List<Session> sessions;
}
