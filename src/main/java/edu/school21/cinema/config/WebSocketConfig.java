package edu.school21.cinema.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//    register endpoint that clients will use
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // withSockJS is used to enable WS if browser of client doesn't support
        registry.addEndpoint("/ws").withSockJS();
    }

//    it uses to route messages from one client to another
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        messages whose destination starts with "/app" should be routes to message-handling methods(@MessageHandling)
        registry.setApplicationDestinationPrefixes("/app");

//        should be routed to the message broker.
//        message broker broadcast messages to all connected clients who are subscribed to a particular topic
//        you can use RabbitMQ or ActiveMQ to broadcast
        registry.enableSimpleBroker("/topic");   // Enables a simple in-memory broker
    }
}
