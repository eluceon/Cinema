package edu.school21.cinema.dao.impl;

import edu.school21.cinema.dao.MovieDao;
import edu.school21.cinema.models.Movie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieDaoImpl implements MovieDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Movie> get(int id) {
        return Optional.ofNullable(entityManager.find(Movie.class, id));
    }

    @Override
    public List<Movie> getAll() {
        return entityManager.createQuery("SELECT m FROM Movie m").getResultList();
    }

    @Override
    public void add(Movie movie) {
        entityManager.persist(movie);
    }

    @Override
    public void update(Movie movie) {
        if (movie.getId() != null && entityManager.find(Movie.class, movie.getId()) != null) {
            entityManager.merge(movie);
        }
    }

    @Override
    public void delete(Movie movie) {
        entityManager.remove(movie);
    }
}
