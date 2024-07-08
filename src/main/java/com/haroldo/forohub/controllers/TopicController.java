package com.haroldo.forohub.controllers;

import com.haroldo.forohub.controllers.dto.TopicDTO;
import com.haroldo.forohub.persistence.entities.Topic;
import com.haroldo.forohub.persistence.repository.TopicRepository;
import com.haroldo.forohub.service.impl.TopicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topics")

public class TopicController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TopicServiceImpl topicServiceImpl;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Topic> topic = topicRepository.findById(id);
        if(!topic.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Topic topicObj = topic.get();

        TopicDTO topicDTO = TopicDTO.builder()
                .id(topicObj.getId())
                .title(topicObj.getTitle())
                .message(topicObj.getMessage())
                .createDate(topicObj.getCreateDate())
                .status(topicObj.isStatus())
                .author(topicObj.getAuthor())
                .course(topicObj.getCourse())
                .responseList(topicObj.getResponseList())
                .build();
        return ResponseEntity.ok(topicDTO);
    }

    @GetMapping()
    public ResponseEntity<?>findAll(){
        List<TopicDTO> topicList = topicServiceImpl.findAll()
                .stream().map(topic -> TopicDTO.builder()
                        .id(topic.getId())
                        .title(topic.getTitle())
                        .message(topic.getMessage())
                        .createDate(topic.getCreateDate())
                        .status(topic.isStatus())
                        .author(topic.getAuthor())
                        .course(topic.getCourse())
                        .responseList(topic.getResponseList())
                        .build()).toList();

        return ResponseEntity.ok(topicList);
    }

    @PostMapping
    public ResponseEntity<?>save(@RequestBody TopicDTO topicDTO) throws URISyntaxException {
        if(topicDTO.getTitle().isBlank() || topicDTO.getMessage().isBlank()){
            return ResponseEntity.badRequest().build();
        }
        Optional<Topic> topicExist = topicRepository.findByMessage(topicDTO.getMessage());
        if(topicExist.isPresent()){
            return ResponseEntity.badRequest().body("Message already exists");
        }

        topicRepository.save(Topic.builder().title(topicDTO.getTitle())
                .message(topicDTO.getMessage())
                .createDate(new Date())
                .author(topicDTO.getAuthor())
                .status(topicDTO.isStatus())
                .course(topicDTO.getCourse())
                .responseList(topicDTO.getResponseList())
                .build());

        return ResponseEntity.created(new URI("/topics")).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity <?> update(@PathVariable Long id, @RequestBody TopicDTO topicDTO){
        Optional<Topic> topic = topicRepository.findById(id);

        if (!topic.isPresent()) return ResponseEntity.notFound().build();

        Topic topicObj = topic.get();
        topicObj.setTitle(topicDTO.getTitle());
        topicObj.setMessage(topicDTO.getMessage());
        topicObj.setStatus(topicDTO.isStatus());
        return ResponseEntity.ok("Topic update");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        if(id == null || !topicRepository.existsById(id)){
            return ResponseEntity.badRequest().build();
        }
        topicRepository.deleteById(id);
        return ResponseEntity.ok("Topic deleted");
    }
}

