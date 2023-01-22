package edu.school21.cinema.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "chat_messages")
public class ChatMessage {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private Type type;
    @Column(name = "content")
    private String content;
    @Column(name = "sender")
    private String sender;
    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime = LocalDateTime.now();

    public enum Type {
        CHAT,
        JOIN,
        LEAVE
    }
}
