package com.haroldo.forohub.persistence.dao;

import com.haroldo.forohub.persistence.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    List<UserEntity> findAll();
    Optional<UserEntity> findById(Long id);
}
