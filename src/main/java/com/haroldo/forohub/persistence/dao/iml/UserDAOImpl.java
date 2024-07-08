package com.haroldo.forohub.persistence.dao.iml;

import com.haroldo.forohub.persistence.entities.UserEntity;
import com.haroldo.forohub.persistence.repository.UserRepository;
import com.haroldo.forohub.persistence.dao.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;
@Component
public class UserDAOImpl implements IUserDAO {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> findAll() {
        return (List<UserEntity>) userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }
}
