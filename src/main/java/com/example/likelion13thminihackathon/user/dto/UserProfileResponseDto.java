package com.example.likelion13thminihackathon.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfileResponseDto {
    private String nickname;
    private String department;
    private int grade;
}

// 홈 화면 프로필 정보 담는 dto

