package edu.school21.cinema.services.impl;

import edu.school21.cinema.dao.MovieHallDao;
import edu.school21.cinema.models.MovieHall;
import edu.school21.cinema.services.MovieHallService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class MovieHallServiceImpl implements MovieHallService {
    private final MovieHallDao movieHallDao;

    @Override
    public MovieHall get(int id) {
        return movieHallDao.get(id).orElse(null);
    }

    @Override
    public List<MovieHall> getAll() {
        return movieHallDao.getAll();
    }

    @Override
    @Transactional
    public void add(MovieHall movieHall) {
        movieHallDao.add(movieHall);
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

    @Override
    public MovieHall findBySerialNumber(Integer serialNumber) {
        return movieHallDao.findBySerialNumber(serialNumber).orElse(null);
    }
}
