package com.example.likelion13thminihackathon.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private Key key; // JWT 서명에 사용할 비밀 키
    private final long TOKEN_VALID_TIME = 1000 * 60 * 60 * 3; // 3시간 동안 유효
    private final String SECRET_KEY = "my-super-secret-jwt-key-for-minihackathon1234567890";

    // 서버 시작 시 한 번 실행돼서 key를 초기화해줌
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // 이메일을 담은 JWT 토큰 생성
    public String createToken(String email) {
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + TOKEN_VALID_TIME); // 만료 시간

        return Jwts.builder()
                .setSubject(email) // 토큰의 주제(subject) 설정: 보통 사용자 식별자 넣음
                .setIssuedAt(now) // 토큰 발급 시간
                .setExpiration(expireTime) // 토큰 만료 시간
                .signWith(key, SignatureAlgorithm.HS256) // 어떤 알고리즘으로 사인할지 (HS256), key 사용
                .compact(); // 문자열로 토큰을 최종 변환
    }
}
