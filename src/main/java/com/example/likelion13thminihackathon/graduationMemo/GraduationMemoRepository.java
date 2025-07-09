package com.example.likelion13thminihackathon.graduationMemo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GraduationMemoRepository extends JpaRepository<GraduationMemoEntity, Long> {

    List<GraduationMemoEntity> findAllByCategory(GraduationMemoCategory category);
}