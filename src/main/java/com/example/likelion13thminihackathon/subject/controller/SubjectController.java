package com.example.likelion13thminihackathon.subject.controller;

import com.example.likelion13thminihackathon.subject.dto.SubjectRequestDto;
import com.example.likelion13thminihackathon.subject.dto.SubjectStatisticResponseDto;
import com.example.likelion13thminihackathon.subject.service.SubjectService;
import com.example.likelion13thminihackathon.subject.service.SubjectStatisticService;
import com.example.likelion13thminihackathon.user.entity.User;
import com.example.likelion13thminihackathon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subject")
public class SubjectController {

    private final UserRepository userRepository;
    private final SubjectService subjectService;
    private final SubjectStatisticService subjectStatisticService;

    // 개별 과목 수정 (실시간 저장)
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateSubject(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id,
            @RequestBody SubjectRequestDto dto) {

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        subjectService.updateSubject(user, id, dto);

        // ✅ Map.of 대신 HashMap 사용 (500 에러 방지)
        Map<String, String> response = new HashMap<>();
        response.put("message", "과목 수정 완료");

        return ResponseEntity.ok(response);
    }

    // 학기별 과목 전체 조회
    @GetMapping
    public ResponseEntity<?> getSubjectsBySemester(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam Integer gradeLevel,
            @RequestParam Integer semester) {

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        return ResponseEntity.ok(subjectService.getSubjectsBySemester(user, gradeLevel, semester));
    }

    // 전체 평점 + 취득 학점 계산 API
    @GetMapping("/statistics")
    public ResponseEntity<SubjectStatisticResponseDto> getStatistics(
            @AuthenticationPrincipal UserDetails userDetails) {

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        SubjectStatisticResponseDto result = subjectStatisticService.calculateAll(user);
        return ResponseEntity.ok(result);
    }
}
