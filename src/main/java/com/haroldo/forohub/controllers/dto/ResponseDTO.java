package com.haroldo.forohub.controllers.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO {
    private Long id;
    @NotNull private String message;
    private Date createDate;
    @NotNull private String author;
    private String solution;
    private String topic;
//    private Topic topic;
}
