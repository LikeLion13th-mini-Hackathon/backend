package com.example.likelion13thminihackathon.subject.entity;

import com.example.likelion13thminihackathon.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String semester; // 수강학기

    private String subjectName; // 과목명

    private Integer credit; // 과목 학점

    private String grade; // 과목 성적


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
