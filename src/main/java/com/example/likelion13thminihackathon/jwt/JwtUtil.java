package com.example.likelion13thminihackathon.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private Key key; // JWT 서명용 키
    private final long TOKEN_VALID_TIME = 1000 * 60 * 60 * 3; // 3시간
    private final String SECRET_KEY = "my-super-secret-jwt-key-for-minihackathon1234567890";

    // 서버 시작 시 키 초기화
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // ✅ JWT 토큰 생성
    public String createToken(String email) {
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + TOKEN_VALID_TIME);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expireTime)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ 토큰에서 이메일 추출
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // ✅ 토큰 만료 여부 확인
    public boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return expiration.before(new Date());
    }

    // ✅ 단순 유효성 검사 (토큰 파싱만)
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println("JWT 유효성 검사 실패: " + e.getMessage());
            return false;
        }
    }

    // ✅ 사용자 정보 기반 토큰 유효성 검사 (정석)
    public boolean validateToken(String token, UserDetails userDetails) {
        String email = getEmailFromToken(token);
        return (email != null && email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
