package edu.school21.cinema.dao;

import edu.school21.cinema.models.Message;

import java.util.List;

public interface MessageDao extends CrudDao<Message> {
    List<Message> getChatHistory(int movieId);
}
