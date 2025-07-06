package com.example.likelion13thminihackathon.achievement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AchievementResponseDto {
    private int totalSemester;
    private int currentSemester;
    private int achievementPercent;
    private int remainingSemester;
    private String message;
}
