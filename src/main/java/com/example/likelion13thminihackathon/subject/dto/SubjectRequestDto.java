package com.example.likelion13thminihackathon.subject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectRequestDto {

    // 과목명은 비워도 됨
    private String subjectName;

    // 학점: 0~3 사이일 수 있음 (nullable로 변경)
    private Integer credit;

    // 성적: A+, A, B+ 등 (nullable 허용)
    private String grade;
}
