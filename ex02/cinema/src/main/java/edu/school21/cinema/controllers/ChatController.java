package edu.school21.cinema.controllers;

import edu.school21.cinema.models.ChatMessage;
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

//    registry.setApplicationDestinationPrefixes("/app")
//    messages from clients
//    routed... | full address - /app/films/{id}/chat/sendMessage
    @MessageMapping("/films/{id}/chat/sendMessage")
    @SendTo("/topic/films/{id}")
    public ChatMessage sendMessage(@Payload ChatMessage message, String json) {
        JSONObject jsonObject = new JSONObject(json);
        message.setMovie(movieService.get(jsonObject.getInt("movieId")));
//        send message to film chat
        return message;
    }

    @MessageMapping("/films/{id}/chat/addUser")
    @SendTo("/topic/films/{id}")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor, @DestinationVariable int id) {
        // Add username and movie id in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("movieId", id);
        return chatMessage;
    }

}
