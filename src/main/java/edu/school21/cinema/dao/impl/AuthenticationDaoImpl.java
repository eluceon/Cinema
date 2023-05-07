package edu.school21.cinema.dao.impl;

import edu.school21.cinema.dao.AuthenticationDao;
import edu.school21.cinema.models.Authentication;
import edu.school21.cinema.models.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthenticationDaoImpl implements AuthenticationDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Authentication> findAllByUserId(int userId) {
        return entityManager.createQuery("select a FROM Authentication a WHERE a.admin.id = :userId")
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public Optional<Authentication> get(int id) {
        return Optional.ofNullable(entityManager.find(Authentication.class, id));
    }

    @Override
    public List<Authentication> getAll() {
        return entityManager.createQuery("SELECT a FROM Authentication a").getResultList();
    }

    @Override
    public void add(Authentication authentication) {
        entityManager.persist(authentication);
    }

    @Override
    public void update(Authentication authentication) {
        if (authentication.getId() != null && entityManager.find(Message.class, authentication.getId()) != null) {
            entityManager.merge(authentication);
        }
    }

    @Override
    public void delete(Authentication authentication) {
        entityManager.remove(authentication);
    }
}
