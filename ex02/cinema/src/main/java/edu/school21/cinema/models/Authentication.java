package edu.school21.cinema.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "authentications")
public class Authentication {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "ip")
    @NotBlank(message = "IP is required")
    private String ip;
    @Column(name = "date_time")
    @NotNull(message = "Date is required")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp dateTime;
    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private Admin admin;
}
