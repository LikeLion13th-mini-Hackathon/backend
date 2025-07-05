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

    // 🔹 카테고리: ENUM으로 변경
    @Enumerated(EnumType.STRING)
    private GraduationMemoCategory category;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // ✅ 수정 기능을 위한 메서드
    public void updateContent(String newContent) {
        this.content = newContent;
    }
}
