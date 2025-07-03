package com.example.likelion13thminihackathon.subject.controller;

import com.example.likelion13thminihackathon.subject.dto.SubjectRequestDto;
import com.example.likelion13thminihackathon.subject.service.SubjectService;
import com.example.likelion13thminihackathon.user.entity.User;
import com.example.likelion13thminihackathon.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subject")
public class SubjectController {

    private final UserRepository userRepository;
    private final SubjectService subjectService; // ✅ 서비스 주입

    // 과목 등록
    @PostMapping
    public ResponseEntity<?> createSubject(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid SubjectRequestDto requestDto) {

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        subjectService.createSubject(requestDto, user); // ✅ 서비스로 위임

        return ResponseEntity.ok("과목 등록 완료");
    }

    // 과목 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubject(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid SubjectRequestDto requestDto) {

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        subjectService.updateSubject(id, requestDto, user); // ✅ 서비스로 위임

        return ResponseEntity.ok("과목 수정 완료");
    }
}
