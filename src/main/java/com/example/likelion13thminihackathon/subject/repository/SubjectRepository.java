package com.example.likelion13thminihackathon.subject.repository;

import com.example.likelion13thminihackathon.subject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByUserIdAndGradeLevelAndSemester(Long userId, Integer gradeLevel, Integer semester);
}
