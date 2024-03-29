package edu.school21.cinema.services.impl;

import edu.school21.cinema.dao.PosterDao;
import edu.school21.cinema.models.Poster;
import edu.school21.cinema.services.PosterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class PosterServiceImpl implements PosterService {
    private final PosterDao posterDao;

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
