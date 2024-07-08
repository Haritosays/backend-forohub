package com.haroldo.forohub.controllers.dto;

import com.haroldo.forohub.persistence.entities.Response;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopicDTO {
    private Long id;
    @NotNull(message = "Title is obligatory")
    private String title;
    @NotNull(message = "Message is obligatory")
    private String message;
    private Date createDate;
    private boolean status;
    private String author;
    private String course;
    private List<Response> responseList = new ArrayList<>();
}
