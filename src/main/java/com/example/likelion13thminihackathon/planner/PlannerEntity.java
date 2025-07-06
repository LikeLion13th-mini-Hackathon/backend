package com.example.likelion13thminihackathon.planner;

import com.example.likelion13thminihackathon.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "planner")
public class PlannerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User 엔티티와 ManyToOne 관계 (user_id 외래키)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 고정값 사용 예정: "1학년1학기" ~ "4학년2학기"
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Semester semester;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(columnDefinition = "TEXT")
    private String goal;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean deletedAt;
}
