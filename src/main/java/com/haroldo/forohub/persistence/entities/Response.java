package com.haroldo.forohub.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "respuestas")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private Date createDate;
    private String author;
    private String solution;
    private String topic;
//    @ManyToOne
//    @JoinColumn(name = "id_topico")
//    private Topic topic;
}
