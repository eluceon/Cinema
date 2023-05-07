package edu.school21.cinema.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@Entity
@Table(name = "authentications")
public class Authentication {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ip")
    @NotBlank(message = "IP is required")
    private String ip;
    @Column(name = "date_time")
    @NotNull(message = "Date is required")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private Admin admin;

    public Authentication(String ip, LocalDateTime dateTime, Admin admin) {
        this.ip = ip;
        this.dateTime = dateTime;
        this.admin = admin;
    }

    public String toDateTimeString(String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}
