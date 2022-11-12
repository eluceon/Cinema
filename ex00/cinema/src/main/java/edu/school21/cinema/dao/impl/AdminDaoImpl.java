package edu.school21.cinema.dao.impl;

import edu.school21.cinema.dao.AdminDao;
import edu.school21.cinema.models.Admin;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AdminDaoImpl implements AdminDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Admin> get(int id) {
        return Optional.ofNullable(entityManager.find(Admin.class, id));
    }

    @Override
    public List<Admin> getAll() {
        return entityManager.createQuery("SELECT a FROM Admin a").getResultList();
    }

    @Override
    public void save(Admin admin) {
        entityManager.persist(admin);
    }

    @Override
    public void update(Admin admin) {
        TypedQuery<Admin> query = entityManager.createQuery(
                "UPDATE Admin a SET a.firstName = :firstName, a.lastName = :lastName, " +
                        "a.phoneNumber = :phoneNumber, a.email = :email, a.password =:password "+
                        "WHERE a.id = :id",
                Admin.class
        );
        query.setParameter("firstName", admin.getFirstName())
                .setParameter("lastName", admin.getLastName())
                .setParameter("phoneNumber", admin.getPhoneNumber())
                .setParameter("email", admin.getEmail())
                .setParameter("password", admin.getPassword())
                .setParameter("id", admin.getId());
    }

    @Override
    public void delete(Admin admin) {
        entityManager.remove(admin);
    }
}
