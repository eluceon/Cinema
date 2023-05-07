package edu.school21.cinema.services;

import edu.school21.cinema.models.Message;

import java.util.List;

public interface MessageService extends Service<Message> {
    List<Message> getHistory(int movieId);
}
