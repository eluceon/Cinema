package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Message;
import edu.school21.cinema.services.MessageService;
import edu.school21.cinema.services.MovieService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ChatController {
    private final MovieService movieService;
    private final MessageService messageService;

//    registry.setApplicationDestinationPrefixes("/app")
//    messages from clients
//    routed... | full address - /app/films/{id}/chat/sendMessage
    @MessageMapping("/films/{id}/chat/sendMessage")
    @SendTo("/topic/films/{id}")
    public Message sendMessage(@Payload Message message, String json) {
        JSONObject jsonObject = new JSONObject(json);
        message.setMovie(movieService.get(jsonObject.getInt("movieId")));

        messageService.add(message);
//        send message to film chat
        return message;
    }

    @MessageMapping("/films/{id}/chat/addUser")
    @SendTo("/topic/films/{id}")
    public Message addUser(@Payload Message message,
                               SimpMessageHeaderAccessor headerAccessor, @DestinationVariable int id) {
        // Add username and movie id in web socket session
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        headerAccessor.getSessionAttributes().put("movieId", id);
        return message;
    }

}
