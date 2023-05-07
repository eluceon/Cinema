package edu.school21.cinema.services;

import edu.school21.cinema.models.Avatar;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AvatarService extends Service<Avatar> {
    List<Avatar> getAllByUserId(Integer userId);
    Optional<Avatar> findByUUID(UUID uuid);
}
