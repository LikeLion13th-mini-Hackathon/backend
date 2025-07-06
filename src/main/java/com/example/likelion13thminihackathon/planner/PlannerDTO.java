package com.example.likelion13thminihackathon.planner;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PlannerDTO {
    private Long id;  // ✅ plannerId 포함
    private Semester semester;
    // 예: "1학년1학기" ~ "4학년2학기"
    private Category category;
    private String goal;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deletedAt;
}
