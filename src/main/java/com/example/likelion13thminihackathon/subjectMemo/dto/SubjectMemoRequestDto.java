package com.example.likelion13thminihackathon.subjectMemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectMemoRequestDto {
    private String memo;
}

// 사용자->서버에게 메모 내용만 전달
