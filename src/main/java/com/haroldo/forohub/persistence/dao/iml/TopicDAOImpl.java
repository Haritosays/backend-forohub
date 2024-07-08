package com.haroldo.forohub.persistence.dao.iml;

import com.haroldo.forohub.persistence.dao.ITopicDAO;
import com.haroldo.forohub.persistence.entities.Topic;
import com.haroldo.forohub.persistence.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TopicDAOImpl implements ITopicDAO {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public List<Topic> findAll() {
        return (List<Topic>) topicRepository.findAll();
    }

    @Override
    public Optional<Topic> findById(Long id) {
        return topicRepository.findById(id);
    }

    @Override
    public Optional<Topic> findByMessage(String message) {
        return topicRepository.findByMessage(message);
    }

    @Override
    public void save(Topic topic) {
        topicRepository.save(topic);
    }

    @Override
    public void deleteById(Long id) {
        topicRepository.deleteById(id);
    }
}
