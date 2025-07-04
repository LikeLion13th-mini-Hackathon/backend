package com.example.likelion13thminihackathon.subjectMemo.controller;

import com.example.likelion13thminihackathon.subjectMemo.dto.SubjectMemoRequestDto;
import com.example.likelion13thminihackathon.subjectMemo.dto.SubjectMemoResponseDto;
import com.example.likelion13thminihackathon.subjectMemo.service.SubjectMemoService;
import com.example.likelion13thminihackathon.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memo")
public class SubjectMemoController {

    private final SubjectMemoService subjectMemoService;

    // 메모 등록 API
    @PostMapping("/{subjectId}")
    public ResponseEntity<?> createMemo(
            @PathVariable Long subjectId,
            @RequestBody SubjectMemoRequestDto requestDto,
            @AuthenticationPrincipal User user
    ) {
        try {
            SubjectMemoResponseDto responseDto = subjectMemoService.createMemo(subjectId, requestDto, user);
            return ResponseEntity.ok(responseDto);
        } catch (IllegalStateException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}
