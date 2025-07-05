package com.example.likelion13thminihackathon.subjectMemo.controller;

import com.example.likelion13thminihackathon.subjectMemo.dto.SubjectMemoRequestDto;
import com.example.likelion13thminihackathon.subjectMemo.dto.SubjectMemoResponseDto;
import com.example.likelion13thminihackathon.subjectMemo.service.SubjectMemoService;
import com.example.likelion13thminihackathon.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/memo")
@RequiredArgsConstructor
public class SubjectMemoController {

    private final SubjectMemoService subjectMemoService;

    // 메모 조회
    @GetMapping("/{subjectId}")
    public ResponseEntity<SubjectMemoResponseDto> getMemo(
            @PathVariable Long subjectId,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(
                subjectMemoService.getMemo(subjectId, user)
        );
    }

    // 메모 생성·수정 (Upsert)
    @PutMapping("/{subjectId}")
    public ResponseEntity<SubjectMemoResponseDto> saveMemo(
            @PathVariable Long subjectId,
            @RequestBody SubjectMemoRequestDto requestDto,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(
                subjectMemoService.saveMemo(subjectId, requestDto, user)
        );
    }

    // 메모 삭제
    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Void> deleteMemo(
            @PathVariable Long subjectId,
            @AuthenticationPrincipal User user
    ) {
        subjectMemoService.deleteMemo(subjectId, user);
        return ResponseEntity.noContent().build();
    }
}
