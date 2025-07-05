package com.example.likelion13thminihackathon.graduationMemo;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class GraduationMemoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 카테고리: "학점", "토익", "졸업요건"
    private String category;

    // 🔹 메모 내용
    @Column(columnDefinition = "TEXT")
    private String content;

    // 🔹 작성 시간
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
