package edu.school21.cinema.dao;

import edu.school21.cinema.models.Admin;

import java.util.Optional;

public interface AdminDao extends CrudDao<Admin> {
    Optional<Admin> findByEmail(String email);
}
