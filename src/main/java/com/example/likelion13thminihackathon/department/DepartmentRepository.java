package com.example.likelion13thminihackathon.department;

import com.example.likelion13thminihackathon.college.CollegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

    // 특정 단과대학에 소속된 학과 목록 조회
    List<DepartmentEntity> findByCollege(CollegeEntity college);
}
