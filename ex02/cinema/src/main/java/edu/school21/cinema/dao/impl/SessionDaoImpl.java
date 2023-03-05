package edu.school21.cinema.dao.impl;

import edu.school21.cinema.dao.SessionDao;
import edu.school21.cinema.models.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class SessionDaoImpl implements SessionDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Session> get(int id) {
        return Optional.ofNullable(entityManager.find(Session.class, id));
    }

    @Override
    public List<Session> getAll() {
        return entityManager.createQuery("SELECT s FROM Session s").getResultList();
    }

    @Override
    public void add(Session session) {
        entityManager.persist(session);
    }

    @Override
    public void update(Session session) {
        entityManager.merge(session);
    }

    @Override
    public void delete(Session session) {
        entityManager.remove(session);
    }

    @Override
    public List<Session> findByMovieTitle(String title) {
        TypedQuery<Session> query = entityManager.createQuery(
                "SELECT s FROM Session s LEFT JOIN FETCH s.movie m " +
                        "WHERE lower(m.title) like lower(concat('%',:title, '%'))",
                Session.class
        );
        return query.setParameter("title", title).getResultList();
    }
}
