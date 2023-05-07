package edu.school21.cinema.services;

import edu.school21.cinema.models.Authentication;

import java.util.List;

public interface AuthenticationService {
    void add(Authentication authentication);
    List<Authentication> getUserAuthHistory(Integer userId);
}
