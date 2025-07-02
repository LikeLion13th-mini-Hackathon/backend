package com.example.likelion13thminihackathon.user.service;

import com.example.likelion13thminihackathon.user.dto.UserRequestDto;
import com.example.likelion13thminihackathon.user.entity.User;
import com.example.likelion13thminihackathon.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    // 생성자 주입
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원가입 로직
    public void signup(UserRequestDto requestDto) {
        User user = new User(requestDto);
        userRepository.save(user);
    }
}
