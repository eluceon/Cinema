package edu.school21.cinema.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "avatars")
public class Avatar {
    @Id
    @Column(name = "id")
    private UUID id;
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
    @Column(name = "date_time")
    @NotNull(message = "Date is required")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp dateTime;
    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private Admin admin;
}
