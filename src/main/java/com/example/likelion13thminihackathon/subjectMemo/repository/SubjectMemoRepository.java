package com.example.likelion13thminihackathon.subjectMemo.repository;

import com.example.likelion13thminihackathon.subjectMemo.entity.SubjectMemo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectMemoRepository extends JpaRepository<SubjectMemo, Long> {
    // subjectId + userId 조합으로 메모 조회
    Optional<SubjectMemo> findBySubjectIdAndUserId(Long subjectId, Long userId);
}
