package com.example.likelion13thminihackathon.planner;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PlannerDTO {
    private String semester; // 예: "2025-1"
    private Category category;
    private String goal;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deletedAt;
}



