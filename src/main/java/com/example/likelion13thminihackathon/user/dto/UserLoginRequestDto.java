/**
 * 로그인 요청 시 사용되는 DTO
 * 사용자가 입력한 이메일과 비밀번호를 담는다.
 */

package com.example.likelion13thminihackathon.user.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class UserLoginRequestDto {

    @Email(message = "이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
