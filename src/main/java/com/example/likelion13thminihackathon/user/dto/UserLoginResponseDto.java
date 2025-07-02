/**
 * 로그인 성공 시 반환되는 DTO
 * JWT 토큰을 담는다.
 */

package com.example.likelion13thminihackathon.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginResponseDto {
    private String token;
}
