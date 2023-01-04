package edu.school21.cinema.services;

import edu.school21.cinema.models.Session;

import java.util.List;

public interface SessionService extends Service<Session> {
    List<Session> searchByMovieTitle(String title);
}
