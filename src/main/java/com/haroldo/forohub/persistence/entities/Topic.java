package com.haroldo.forohub.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "topicos")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private Date createDate;
    private boolean status;
//    @ManyToOne
//    private UserEntity author;
    private String author;
//    @ManyToOne
//    private Course course;
    private String course;
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Response> responseList = new ArrayList<>();
}
