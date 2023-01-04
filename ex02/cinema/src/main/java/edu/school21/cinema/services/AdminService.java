package edu.school21.cinema.services;

import edu.school21.cinema.models.Admin;

import java.util.Optional;

public interface AdminService extends Service<Admin> {
    Admin signIn(String email, String password);

    Admin findByEmail(String email);
}
