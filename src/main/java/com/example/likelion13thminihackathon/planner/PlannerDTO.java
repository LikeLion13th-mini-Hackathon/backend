package com.example.likelion13thminihackathon.planner;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PlannerDTO {
    private String semester;
    private Category category;  // Enum 타입
    private String goal;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deletedAt;
}

