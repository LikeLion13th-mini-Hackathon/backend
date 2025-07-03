package com.example.likelion13thminihackathon.subject.dto;

import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectRequestDto {

    private String subjectName;

    @Min(value = 1, message = "학점은 1 이상이어야 합니다.")
    @Max(value = 3, message = "학점은 3 이하이어야 합니다.")
    private int credit;

    private String grade;

    @NotNull
    private Integer gradeLevel;
    @NotNull
    private Integer semester;
}
