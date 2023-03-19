package edu.school21.cinema.dao;

import edu.school21.cinema.models.Authentication;

import java.util.List;

public interface AuthenticationDao extends CrudDao<Authentication> {
    List<Authentication> findAllByUserId(int userId);
}
