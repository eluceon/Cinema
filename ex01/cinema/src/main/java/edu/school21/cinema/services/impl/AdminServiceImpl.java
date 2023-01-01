package edu.school21.cinema.services.impl;

import edu.school21.cinema.dao.AdminDao;
import edu.school21.cinema.models.Admin;
import edu.school21.cinema.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService {
    private final AdminDao adminDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminServiceImpl(AdminDao adminDao, PasswordEncoder passwordEncoder) {
        this.adminDao = adminDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Admin get(int id) {
        return adminDao.get(id).orElse(null);
    }

    @Override
    public List<Admin> getAll() {
        return adminDao.getAll();
    }

    @Override
    @Transactional
    public void add(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminDao.add(admin);
    }

    @Override
    @Transactional
    public void update(Admin admin) {
        adminDao.update(admin);
    }

    @Override
    @Transactional
    public void delete(Admin admin) {
        adminDao.delete(admin);
    }

    @Override
    public Admin signIn(String email, String password) {
        Admin admin = adminDao.findByEmail(email).orElse(null);
        return (admin == null || !passwordEncoder.matches(password, admin.getPassword())) ? null : admin;
    }

    @Override
    public Admin findByEmail(String email) {
        return adminDao.findByEmail(email).orElse(null);
    }
}
