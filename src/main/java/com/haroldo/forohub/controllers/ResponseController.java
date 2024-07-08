package com.haroldo.forohub.controllers;

import com.haroldo.forohub.controllers.dto.ResponseDTO;
import com.haroldo.forohub.controllers.dto.TopicDTO;
import com.haroldo.forohub.persistence.entities.Response;
import com.haroldo.forohub.persistence.repository.ResponseRepository;
import com.haroldo.forohub.service.impl.ResponseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/responses")

public class ResponseController {
    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private ResponseServiceImpl responseServiceImp;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Response> resp = responseRepository.findById(id);
        if(!resp.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Response responsesObj = resp.get();

        ResponseDTO responseDTO = ResponseDTO.builder()
                .id(responsesObj.getId())
                .message(responsesObj.getMessage())
                .createDate(responsesObj.getCreateDate())
                .author(responsesObj.getAuthor())
                .solution(responsesObj.getSolution())
                .build();

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<ResponseDTO> responses = responseServiceImp.findAll()
                .stream().map(response -> ResponseDTO.builder()
                        .id(response.getId())
                        .message(response.getMessage())
                        .createDate(response.getCreateDate())
                        .author(response.getAuthor())
                        .solution(response.getSolution())
                        .build()).toList();

        return ResponseEntity.ok(responses);
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody ResponseDTO responseDTO)throws URISyntaxException {
        if( responseDTO.getMessage().isBlank()){
            return ResponseEntity.badRequest().body("Message cannot be empty");
        }
        Optional<Response> topicExist = responseRepository.findByMessage(responseDTO.getMessage());
        if(topicExist.isPresent()){
            return ResponseEntity.badRequest().body("Message already exists");
        }
        responseRepository.save(Response.builder()
                .message(responseDTO.getMessage())
                .createDate(new Date())
                .author(responseDTO.getAuthor())
                .build());

        return ResponseEntity.created(new URI("/responses")).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity <?> update(@PathVariable Long id, @RequestBody TopicDTO topicDTO){
        Optional<Response> response = responseRepository.findById(id);
        if (!response.isPresent()) return ResponseEntity.notFound().build();
        Response responseObj = response.get();
        responseObj.setMessage(topicDTO.getMessage());
        return ResponseEntity.ok("Response updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        if(id == null || !responseRepository.existsById(id)){
            return ResponseEntity.badRequest().build();
        }
        responseRepository.deleteById(id);
        return ResponseEntity.ok("Response deleted");
    }
}
