package edu.school21.cinema.dao.impl;

import edu.school21.cinema.dao.PosterDao;
import edu.school21.cinema.models.Poster;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class PosterImpl implements PosterDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Poster> get(int id) {
        return Optional.ofNullable(entityManager.find(Poster.class, id));
    }

    @Override
    public List<Poster> getAll() {
        return entityManager.createQuery("SELECT p FROM Poster p").getResultList();
    }

    @Override
    public void save(Poster poster) {
        entityManager.persist(poster);
    }

    @Override
    public void update(Poster poster) {
        entityManager.merge(poster);
    }

    @Override
    public void delete(Poster poster) {
        entityManager.remove(poster);
    }
}
