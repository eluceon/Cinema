package edu.school21.cinema.dao;

import edu.school21.cinema.models.Session;

import java.util.List;

public interface SessionDao extends CrudDao<Session> {
    List<Session> findByMovieTitle(String title);
}
