package com.example.likelion13thminihackathon.subjectMemo.entity;

import com.example.likelion13thminihackathon.subject.entity.Subject;
import com.example.likelion13thminihackathon.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class SubjectMemo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 로그인한 사용자와 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 메모를 작성한 과목과 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    // 메모 내용
    @Column(columnDefinition = "TEXT")
    private String memo;

    // 생성자
    @Builder
    public SubjectMemo(User user, Subject subject, String memo) {
        this.user = user;
        this.subject = subject;
        this.memo = memo;
    }
}
