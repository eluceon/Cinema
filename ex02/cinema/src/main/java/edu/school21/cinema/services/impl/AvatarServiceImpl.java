package edu.school21.cinema.services.impl;

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
    @Override
    public List<Avatar> getAllByUserId(Integer userId) {
        return null;
    }

    @Override
    public Optional<Avatar> findByUUID(UUID UUID) {
        return Optional.empty();
    }

    @Override
    public Avatar get(int id) {
        return null;
    }

    @Override
    public List<Avatar> getAll() {
        return null;
    }

    @Override
    public void add(Avatar entity) {

    }

    @Override
    public void update(Avatar entity) {

    }

    @Override
    public void delete(Avatar entity) {

    }
}
