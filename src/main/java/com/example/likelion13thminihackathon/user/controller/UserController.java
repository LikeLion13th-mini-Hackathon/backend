package com.example.likelion13thminihackathon.user.controller;

import com.example.likelion13thminihackathon.user.dto.UserRequestDto;
import com.example.likelion13thminihackathon.user.dto.UserLoginRequestDto;
import com.example.likelion13thminihackathon.user.dto.UserLoginResponseDto;
import com.example.likelion13thminihackathon.user.dto.UserProfileResponseDto;
import com.example.likelion13thminihackathon.user.entity.User;
import com.example.likelion13thminihackathon.user.repository.UserRepository;
import com.example.likelion13thminihackathon.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    // 생성자 주입
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody UserRequestDto requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.ok(Map.of("message", "회원가입 성공!"));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto requestDto) {
        String token = userService.login(requestDto);
        return ResponseEntity.ok(new UserLoginResponseDto(token));
    }

    // 홈 화면 나의 프로필 조회
    @GetMapping("/user/profile")
    public ResponseEntity<UserProfileResponseDto> getUserProfile(
            @AuthenticationPrincipal UserDetails userDetails) {

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        UserProfileResponseDto dto = new UserProfileResponseDto(
                user.getNickname(),
                user.getDepartment(),
                user.getGrade()
        );

        return ResponseEntity.ok(dto);
    }
}
