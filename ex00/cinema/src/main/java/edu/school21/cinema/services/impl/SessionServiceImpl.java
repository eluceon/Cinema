package edu.school21.cinema.services.impl;

import edu.school21.cinema.dao.SessionDao;
import edu.school21.cinema.models.Session;
import edu.school21.cinema.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SessionServiceImpl implements SessionService {
    private final SessionDao sessionDao;

    @Autowired
    public SessionServiceImpl(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    @Override
    public Session get(int id) {
        return sessionDao.get(id).orElse(null);
    }

    @Override
    public List<Session> getAll() {
        return sessionDao.getAll();
    }

    @Override
    @Transactional
    public void add(Session session) {
        sessionDao.add(session);
    }

    @Override
    @Transactional
    public void update(Session session) {
        sessionDao.update(session);
    }

    @Override
    @Transactional
    public void delete(Session session) {
        sessionDao.delete(session);
    }
}
