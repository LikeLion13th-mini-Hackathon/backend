package com.example.likelion13thminihackathon.subject.repository;

import com.example.likelion13thminihackathon.subject.entity.Subject;
import com.example.likelion13thminihackathon.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    // 학기별 전체 과목 조회
    List<Subject> findByUserIdAndGradeLevelAndSemester(Long userId, Integer gradeLevel, Integer semester);

    // 또는 유저 객체로 받아오는 형태 (Service 단에서 유연하게 사용 가능)
    List<Subject> findByUserAndGradeLevelAndSemester(User user, Integer gradeLevel, Integer semester);
}
