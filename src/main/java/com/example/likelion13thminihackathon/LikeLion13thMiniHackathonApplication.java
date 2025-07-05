package com.example.likelion13thminihackathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing; // ✅ 추가된 import

@SpringBootApplication
@EnableJpaAuditing  // ✅ 작성일자(예: createdAt) 자동 기록을 위해 필수
public class LikeLion13thMiniHackathonApplication {

    public static void main(String[] args) {
        SpringApplication.run(LikeLion13thMiniHackathonApplication.class, args);
    }

}
