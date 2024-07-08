package com.haroldo.forohub.service.impl;


import com.haroldo.forohub.persistence.entities.Topic;
import com.haroldo.forohub.persistence.dao.ITopicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements ITopicDAO {

    @Autowired
    private  ITopicDAO topicDAO;


    @Override
    public List<Topic> findAll() {
        return topicDAO.findAll();
    }

    @Override
    public Optional<Topic> findById(Long id) {
        return topicDAO.findById(id);
    }

    @Override
    public Optional<Topic> findByMessage(String message) {
        return topicDAO.findByMessage(message);
    }

    @Override
    public void save(Topic topic) {
        topicDAO.save(topic);
    }

    @Override
    public void deleteById(Long id) {
        topicDAO.deleteById(id);
    }
}
