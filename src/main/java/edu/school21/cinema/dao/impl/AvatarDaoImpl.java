package edu.school21.cinema.dao.impl;

import edu.school21.cinema.dao.AvatarDao;
import edu.school21.cinema.models.Avatar;
import edu.school21.cinema.models.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AvatarDaoImpl implements AvatarDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Avatar> getAllByUserId(int userId) {
        return entityManager.createQuery("select a FROM Avatar a WHERE admin.id = :userId")
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public Optional<Avatar> getByUUID(UUID uuid) {
        return Optional.ofNullable(entityManager.find(Avatar.class, uuid));
    }

    @Override
    public Optional<Avatar> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Avatar> getAll() {
        return entityManager.createQuery("SELECT a FROM Avatar a").getResultList();
    }

    @Override
    public void add(Avatar avatar) {
        entityManager.persist(avatar);
    }

    @Override
    public void update(Avatar avatar) {
        if (avatar.getId() != null && entityManager.find(Message.class, avatar.getId()) != null) {
            entityManager.merge(avatar);
        }
    }

    @Override
    public void delete(Avatar avatar) {
        entityManager.remove(avatar);
    }
}
