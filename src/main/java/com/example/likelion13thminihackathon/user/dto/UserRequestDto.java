package com.example.likelion13thminihackathon.user.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Getter
@Setter
public class UserRequestDto {
    @NotBlank(message = "닉네임은 필수입니다.")
    private String nickname;

    @Email(message = "이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @NotNull(message = "생년월일은 필수입니다.")
    private LocalDate birthdate; // 생년월일

    @NotBlank(message = "학과는 필수입니다.")
    private String department; // 학과

    @Min(value = 1, message = "학년은 1 이상이어야 합니다.")
    @Max(value = 6, message = "학년은 6 이하이어야 합니다.")
    private int grade; // 학년

    private boolean termsAgreed; // 서비스 동의
    private boolean marketingAgreed; // 마케팅 동의
}
