// 위치: com.example.likelion13thminihackathon.subject.repository

package com.example.likelion13thminihackathon.subject.repository;

import com.example.likelion13thminihackathon.subject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
