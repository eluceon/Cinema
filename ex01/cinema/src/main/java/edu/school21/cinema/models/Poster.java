package edu.school21.cinema.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

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
    @NotBlank(message = "Name is required")
    private String name;
    @Column(name = "path")
    @NotBlank(message = "Path is required")
    private String path;
    @Column(name = "size")
    @Min(value = 0, message = "Size can't be a negative number")
    private Long size;
    @Column(name = "mime")
    private String mime;
    @Column(name = "date")
    @NotNull(message = "Date is required")
    private Timestamp dateTime;
    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private Admin admin;
}
