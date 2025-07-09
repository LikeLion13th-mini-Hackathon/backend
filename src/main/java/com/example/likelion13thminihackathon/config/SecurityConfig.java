package com.example.likelion13thminihackathon.config;

import com.example.likelion13thminihackathon.jwt.JwtAuthenticationFilter;
import com.example.likelion13thminihackathon.jwt.JwtUtil;
import com.example.likelion13thminihackathon.user.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtUtil, customUserDetailsService);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors
                        .configurationSource(corsConfigurationSource())
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                                // ✅ OPTIONS 요청(CORS preflight) 허용
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                                // ✅ 개발 중 전체 허용 (배포 시 필요한 API만 제한하세요)
                                .requestMatchers("/**").permitAll()

                        // 🔒 배포용 설정 예시 (나중에 교체 가능)
                        // .requestMatchers("/api/auth/**", "/api/login", "/api/signup").permitAll()
                        // .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // 허용할 프론트 도메인들 (Netlify 등)
        config.setAllowedOrigins(List.of(
                "https://babyhackathon-test.netlify.app", // 프론트 테스트 도메인
                "https://chukchuk-haksa.netlify.app", // 프론트 도메인
                "http://13.125.232.46",
                "https://chukchuk-haksa.cloud", // 백엔드 도메인
                "http://localhost:3000" // 프론트 로컬 테스트
        ));

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
