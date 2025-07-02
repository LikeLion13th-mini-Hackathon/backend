package com.example.likelion13thminihackathon.user.entity;
import com.example.likelion13thminihackathon.user.dto.UserRequestDto;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private int grade;

    @Column(nullable = false)
    private boolean termsAgreed;

    @Column(nullable = false)
    private boolean marketingAgreed;

    public User(UserRequestDto dto) {
        this.nickname = dto.getNickname();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.birthdate = dto.getBirthdate();
        this.department = dto.getDepartment();
        this.grade = dto.getGrade();
        this.termsAgreed = dto.isTermsAgreed();
        this.marketingAgreed = dto.isMarketingAgreed();
    }
}
