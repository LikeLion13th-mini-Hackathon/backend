package com.example.likelion13thminihackathon.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserMypageResponseDto {
    private String nickname;
    private String email;
    private LocalDate birthdate;
    private String department;
}
