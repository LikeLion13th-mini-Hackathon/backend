package com.example.likelion13thminihackathon.subjectMemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SubjectMemoResponseDto {
    private Long subjectId;
    private String subjectName;
    private String memo;
}
