package com.haroldo.forohub.service;

import com.haroldo.forohub.persistence.entities.Topic;

import java.util.List;
import java.util.Optional;

public interface ITopicService {

    List<Topic> findAll();
    Optional<Topic> findById(Long id);

    void save(Topic topic);
    void deleteById(Long id);
}
