package edu.school21.cinema.services.impl;

import edu.school21.cinema.dao.AdminDao;
import edu.school21.cinema.models.Admin;
import edu.school21.cinema.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService {
    private final AdminDao adminDao;

    @Autowired
    public AdminServiceImpl(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public Optional<Admin> get(int id) {
        return adminDao.get(id);
    }

    @Override
    public List<Admin> getAll() {
        return adminDao.getAll();
    }

    @Override
    @Transactional
    public void save(Admin admin) {
        adminDao.save(admin);
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
}
