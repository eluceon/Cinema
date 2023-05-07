package edu.school21.cinema.services.impl;

import edu.school21.cinema.dao.AvatarDao;
import edu.school21.cinema.models.Avatar;
import edu.school21.cinema.services.AvatarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class AvatarServiceImpl implements AvatarService {
    private final AvatarDao avatarDao;
    @Override
    public List<Avatar> getAllByUserId(Integer userId) {
        return avatarDao.getAllByUserId(userId);
    }

    @Override
    public Optional<Avatar> findByUUID(UUID uuid) {
        return avatarDao.getByUUID(uuid);
    }

    @Override
    public Avatar get(int id) {
        return avatarDao.get(id).orElse(null);
    }

    @Override
    public List<Avatar> getAll() {
        return avatarDao.getAll();
    }

    @Override
    @Transactional
    public void add(Avatar avatar) {
        avatarDao.add(avatar);
    }

    @Override
    public void update(Avatar avatar) {
        avatarDao.update(avatar);
    }

    @Override
    public void delete(Avatar avatar) {
        avatarDao.delete(avatar);
    }
}
