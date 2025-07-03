package com.example.likelion13thminihackathon.user.service;

import com.example.likelion13thminihackathon.user.dto.UserRequestDto;
import com.example.likelion13thminihackathon.user.dto.UserLoginRequestDto;
import com.example.likelion13thminihackathon.user.entity.User;
import com.example.likelion13thminihackathon.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Objects;
import com.example.likelion13thminihackathon.jwt.JwtUtil;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // 생성자 주입
    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // 회원가입 로직
    public void signup(UserRequestDto requestDto) {
        User user = new User(requestDto);
        userRepository.save(user);
    }

    // 로그인 로직
    public String login(UserLoginRequestDto requestDto) {
        // 1. 이메일로 유저 조회
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));


        // 2. 비밀번호가 일치하는지 확인
        if (!Objects.equals(user.getPassword(), requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 3. JWT 토큰 생성
        String token = jwtUtil.createToken(user.getEmail());

        return token;
    }
}
