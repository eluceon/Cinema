package edu.school21.cinema.services.impl;

import edu.school21.cinema.dao.MovieDao;
import edu.school21.cinema.models.Movie;
import edu.school21.cinema.services.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    @Override
    public Movie get(int id) {
        return movieDao.get(id).orElse(null);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    @Transactional
    public void add(Movie movie) {
        movieDao.add(movie);
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
