package edu.school21.cinema.services;

import java.util.List;
public interface Service<T> {
    T get(int id);

    List<T> getAll();

    void add(T entity);

    void update(T entity);

    void delete(T entity);
}
