package edu.school21.cinema.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "posters")
public class Poster {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    @NotNull
    @NotEmpty
    private String name;
    @Column(name = "path")
    @NotNull
    @NotEmpty
    private String path;
    @Column(name = "size")
    @NotNull
    @Min(value = 0, message = "Size can't be a negative number")
    private Integer size;
    @Column(name = "mime")
    private String mime;
    @Column(name = "date")
    @NotNull
    private Timestamp date;
    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private Admin admin;
}
