package edu.school21.cinema.dao;

import edu.school21.cinema.models.Avatar;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AvatarDao extends CrudDao<Avatar> {
    List<Avatar> getAllByUserId(int userId);
    Optional<Avatar> getByUUID(UUID uuid);
}
