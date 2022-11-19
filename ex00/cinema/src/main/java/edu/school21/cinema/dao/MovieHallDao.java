package edu.school21.cinema.dao;

import edu.school21.cinema.models.Admin;
import edu.school21.cinema.models.MovieHall;

import java.util.Optional;

public interface MovieHallDao extends CrudDao<MovieHall> {
    Optional<MovieHall> findBySerialNumber(Integer serialNumber);
}
