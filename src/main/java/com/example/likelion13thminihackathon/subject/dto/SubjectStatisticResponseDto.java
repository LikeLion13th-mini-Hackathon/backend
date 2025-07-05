package com.example.likelion13thminihackathon.subject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubjectStatisticResponseDto {
    private double gpa;          // 전체 평점
    private int acquiredCredit; // 취득 학점
}
