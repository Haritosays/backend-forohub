package com.haroldo.forohub.persistence.dao;

import com.haroldo.forohub.persistence.entities.Topic;

import java.util.List;
import java.util.Optional;

public interface ITopicDAO {

    List<Topic> findAll();
    Optional<Topic> findById(Long id);
    Optional<Topic> findByMessage(String message);

    void save(Topic topic);
    void deleteById(Long id);
}
