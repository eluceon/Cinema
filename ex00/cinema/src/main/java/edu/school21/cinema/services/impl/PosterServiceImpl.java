package edu.school21.cinema.services.impl;

import edu.school21.cinema.dao.PosterDao;
import edu.school21.cinema.models.Poster;
import edu.school21.cinema.services.PosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PosterServiceImpl implements PosterService {
    private final PosterDao posterDao;

    @Autowired
    public PosterServiceImpl(PosterDao posterDao) {
        this.posterDao = posterDao;
    }

    @Override
    public Poster get(int id) {
        return posterDao.get(id).orElse(null);
    }

    @Override
    public List<Poster> getAll() {
        return posterDao.getAll();
    }

    @Override
    @Transactional
    public void add(Poster poster) {
        poster.setDateTime(new Timestamp(System.currentTimeMillis()));
        posterDao.add(poster);
    }

    @Override
    @Transactional
    public void update(Poster poster) {
        poster.equals(poster);
    }

    @Override
    @Transactional
    public void delete(Poster poster) {
        posterDao.delete(poster);
    }
}
