package edu.school21.cinema.services.impl;

import edu.school21.cinema.dao.MessageDao;
import edu.school21.cinema.models.Message;
import edu.school21.cinema.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageDao messageDao;

    @Override
    public Message get(int id) {
        return messageDao.get(id).orElse(null);
    }

    @Override
    public List<Message> getAll() {
        return messageDao.getAll();
    }

    @Override
    @Transactional
    public void add(Message message) {
        messageDao.add(message);
    }

    @Override
    @Transactional
    public void update(Message message) {
        messageDao.update(message);
    }

    @Override
    @Transactional
    public void delete(Message message) {
        messageDao.delete(message);
    }

    @Override
    public List<Message> getHistory(int movieId) {
        return messageDao.getChatHistory(movieId);
    }
}
