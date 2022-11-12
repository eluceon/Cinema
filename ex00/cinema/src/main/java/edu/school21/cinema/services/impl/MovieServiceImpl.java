package edu.school21.cinema.services.impl;

import edu.school21.cinema.dao.MovieDao;
import edu.school21.cinema.models.Movie;
import edu.school21.cinema.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    @Autowired
    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Optional<Movie> get(int id) {
        return movieDao.get(id);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    @Transactional
    public void save(Movie movie) {
        movieDao.save(movie);
    }

    @Override
    @Transactional
    public void update(Movie movie) {
        movieDao.update(movie);
    }

    @Override
    @Transactional
    public void delete(Movie movie) {
        movieDao.delete(movie);
    }
}
