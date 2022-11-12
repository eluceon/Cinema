package edu.school21.cinema.services.impl;

import edu.school21.cinema.dao.MovieHallDao;
import edu.school21.cinema.models.MovieHall;
import edu.school21.cinema.services.MovieHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MovieHallServiceImpl implements MovieHallService {
    private final MovieHallDao movieHallDao;

    @Autowired
    public MovieHallServiceImpl(MovieHallDao movieHallDao) {
        this.movieHallDao = movieHallDao;
    }

    @Override
    public Optional<MovieHall> get(int id) {
        return movieHallDao.get(id);
    }

    @Override
    public List<MovieHall> getAll() {
        return movieHallDao.getAll();
    }

    @Override
    @Transactional
    public void save(MovieHall movieHall) {
        movieHallDao.save(movieHall);
    }

    @Override
    @Transactional
    public void update(MovieHall movieHall) {
        movieHallDao.update(movieHall);
    }

    @Override
    @Transactional
    public void delete(MovieHall movieHall) {
        movieHallDao.delete(movieHall);
    }
}
