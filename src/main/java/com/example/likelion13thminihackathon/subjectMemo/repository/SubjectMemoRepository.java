package com.example.likelion13thminihackathon.subjectMemo.repository;

import com.example.likelion13thminihackathon.subjectMemo.entity.SubjectMemo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectMemoRepository extends JpaRepository<SubjectMemo, Long> {

    // 특정 subjectId와 userId에 해당하는 메모가 존재하는지 확인
    boolean existsBySubjectIdAndUserId(Long subjectId, Long userId);
}
