package com.example.likelion13thminihackathon.graduationMemo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class GraduationMemoDTO {
    private String category;
    private String content;
    private LocalDateTime createdAt;  // 🔹 작성일자 포함
}
