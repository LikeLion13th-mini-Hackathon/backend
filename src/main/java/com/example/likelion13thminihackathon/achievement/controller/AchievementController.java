package com.example.likelion13thminihackathon.achievement.controller;

import com.example.likelion13thminihackathon.achievement.dto.AchievementRequestDto;
import com.example.likelion13thminihackathon.achievement.dto.AchievementResponseDto;
import com.example.likelion13thminihackathon.achievement.service.AchievementService;
import com.example.likelion13thminihackathon.user.entity.User;
import com.example.likelion13thminihackathon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/achievement")
@RequiredArgsConstructor
public class AchievementController {

    private final AchievementService service;
    private final UserRepository userRepository;

    // 저장 또는 수정
    @PostMapping
    public ResponseEntity<AchievementResponseDto> saveOrUpdate(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody AchievementRequestDto dto) {

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        AchievementResponseDto response = service.saveOrUpdate(user.getId(), dto);
        return ResponseEntity.ok(response);
    }

    // 조회
    @GetMapping
    public ResponseEntity<AchievementResponseDto> getAchievement(
            @AuthenticationPrincipal UserDetails userDetails) {

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        AchievementResponseDto response = service.getByUser(user.getId());
        return ResponseEntity.ok(response);
    }
}
