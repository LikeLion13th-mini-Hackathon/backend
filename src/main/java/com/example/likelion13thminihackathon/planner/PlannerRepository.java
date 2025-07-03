package com.example.likelion13thminihackathon.planner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlannerRepository extends JpaRepository<PlannerEntity, Long> {
    // 기본 CRUD 메서드 자동 생성됨
}
