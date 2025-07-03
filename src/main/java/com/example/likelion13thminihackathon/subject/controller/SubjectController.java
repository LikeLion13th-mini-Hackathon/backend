package com.example.likelion13thminihackathon.subject.controller;

import com.example.likelion13thminihackathon.subject.dto.SubjectRequestDto;
import com.example.likelion13thminihackathon.subject.entity.Subject;
import com.example.likelion13thminihackathon.subject.repository.SubjectRepository;
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

    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    // 과목 등록
    @PostMapping
    public ResponseEntity<?> createSubject(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid SubjectRequestDto requestDto) {

        // 인증된 사용자 정보로부터 이메일 기반 User 엔티티 조회
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        // 과목 생성
        Subject subject = Subject.builder()
                .semester(requestDto.getSemester())
                .subjectName(requestDto.getSubjectName())
                .credit(requestDto.getCredit())
                .grade(requestDto.getGrade())
                .user(user)
                .build();

        subjectRepository.save(subject);

        return ResponseEntity.ok("과목 등록 완료");
    }
}
