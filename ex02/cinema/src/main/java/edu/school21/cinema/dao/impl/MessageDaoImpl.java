package edu.school21.cinema.dao.impl;

import edu.school21.cinema.dao.MessageDao;
import edu.school21.cinema.models.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class MessageDaoImpl implements MessageDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Message> get(int id) {
        return Optional.ofNullable(entityManager.find(Message.class, id));
    }

    @Override
    public List<Message> getAll() {
        return entityManager.createQuery("SELECT m FROM Message m").getResultList();
    }

    @Override
    public void add(Message message) {
        entityManager.persist(message);
    }


    @Override
    public void update(Message message) {
        if (message.getId() != null && entityManager.find(Message.class, message.getId()) != null) {
            entityManager.merge(message);
        }
    }

    @Override
    public void delete(Message message) {
        entityManager.remove(message);
    }

    @Override
    public List<Message> getChatHistory(int movieId) {
        List<Message> messages = entityManager
                .createQuery("SELECT m FROM Message m " +
                        "WHERE m.movie.id = :movieId " +
                        "ORDER BY m.id DESC"
                , Message.class)
                .setParameter("movieId", movieId)
                .setMaxResults(20)
                .getResultList();
        Collections.reverse(messages);
        return messages;
    }
}
