package edu.school21.cinema.dao.impl;

import edu.school21.cinema.dao.MovieHallDao;
import edu.school21.cinema.models.MovieHall;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieHallDaoImpl implements MovieHallDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<MovieHall> get(int id) {
        return Optional.ofNullable(entityManager.find(MovieHall.class, id));
    }

    @Override
    public List<MovieHall> getAll() {
        return entityManager.createQuery("SELECT m FROM MovieHall m").getResultList();
    }

    @Override
    public void add(MovieHall movieHall) {
        entityManager.persist(movieHall);
    }

    @Override
    public void update(MovieHall movieHall) {
        if (movieHall.getId() != null && entityManager.find(MovieHall.class, movieHall.getId()) != null) {
            entityManager.merge(movieHall);
        }
    }

    @Override
    public void delete(MovieHall movieHall) {
        entityManager.remove(movieHall);
    }
}
