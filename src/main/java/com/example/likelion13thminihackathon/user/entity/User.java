package com.example.likelion13thminihackathon.user.entity;

import com.example.likelion13thminihackathon.user.dto.UserMypageUpdateRequestDto;
import com.example.likelion13thminihackathon.user.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements UserDetails {

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


    public User(UserRequestDto dto) {
        this.nickname = dto.getNickname();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.birthdate = dto.getBirthdate();
        this.department = dto.getDepartment();
        this.grade = dto.getGrade();
    }

    // 마이페이지 수정용 메서드
    public void updateMypage(UserMypageUpdateRequestDto dto) {
        this.nickname = dto.getNickname();
        this.birthdate = dto.getBirthdate();
        this.email = dto.getEmail();
        this.department = dto.getDepartment();
    }

    // UserDetails 필수 구현 메서드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // 권한 없음
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
