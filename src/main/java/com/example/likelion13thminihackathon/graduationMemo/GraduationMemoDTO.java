package com.example.likelion13thminihackathon.graduationMemo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class GraduationMemoDTO {
    private Long id;
    private GraduationMemoCategory category;
    private String content;
    private LocalDateTime createdAt;
}
