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

    @Column(nullable = false)
    private Integer gradeLevel; // 1 ~ 6학년

    @Column(nullable = false)
    private Integer semester; // 1 or 2학기

    @Column(nullable = true)
    private String subjectName; // 과목명 (비워도 가능)

    @Column(nullable = true)
    private Integer credit; // 학점 (학점 null이면 계산 제외)

    @Column(nullable = true)
    private String grade; // 성적 (성적 null이면 계산 제외)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
