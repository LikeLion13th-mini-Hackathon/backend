package com.example.likelion13thminihackathon.subject.dto;

import com.example.likelion13thminihackathon.subject.entity.Subject;
import lombok.Getter;

@Getter
public class SubjectResponseDto {

    private Long id;
    private String subjectName;
    private Integer credit;
    private String grade;
    private Integer gradeLevel; // 학년
    private Integer semester;   // 학기

    public SubjectResponseDto(Subject subject) {
        this.id = subject.getId();
        this.subjectName = subject.getSubjectName();
        this.credit = subject.getCredit();
        this.grade = subject.getGrade();
        this.gradeLevel = subject.getGradeLevel();
        this.semester = subject.getSemester();
    }
}

// 조회한 과목 리스트를 프론트할 때 보낼 때