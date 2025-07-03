package com.example.likelion13thminihackathon.subject.dto;

import com.example.likelion13thminihackathon.subject.entity.Subject;
import lombok.Getter;

// 학기별로 과목 전체 조회
@Getter
public class SubjectResponseDto {
    private Long id;
    private String subjectName;
    private Integer credit;
    private String grade;

    public SubjectResponseDto(Subject subject) {
        this.id = subject.getId();
        this.subjectName = subject.getSubjectName();
        this.credit = subject.getCredit();
        this.grade = subject.getGrade();
    }
}
