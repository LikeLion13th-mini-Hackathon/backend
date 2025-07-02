package com.example.likelion13thminihackathon.user.controller;

import com.example.likelion13thminihackathon.user.dto.UserRequestDto;
import com.example.likelion13thminihackathon.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.likelion13thminihackathon.user.dto.UserLoginRequestDto;
import com.example.likelion13thminihackathon.user.dto.UserLoginResponseDto;


@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    // 생성자 주입
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 API
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserRequestDto requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.ok("회원가입 성공!");
    }

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto requestDto) {
        String token = userService.login(requestDto);
        return ResponseEntity.ok(new UserLoginResponseDto(token));
    }
}
