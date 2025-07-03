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

    // User 엔티티와 ManyToOne 관계, user_id 외래키 컬럼
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 20)
    private String semester;

    @Enumerated(EnumType.STRING)  // Enum 문자열 저장
    @Column(nullable = false)
    private Category category;  // 이제 String 대신 Category enum 타입으로

    @Column(columnDefinition = "TEXT")
    private String goal;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean deletedAt;
}
