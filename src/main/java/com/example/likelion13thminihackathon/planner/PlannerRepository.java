package com.example.likelion13thminihackathon.planner;

import com.example.likelion13thminihackathon.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlannerRepository extends JpaRepository<PlannerEntity, Long> {

    // 특정 유저 전체 플래너 조회
    List<PlannerEntity> findAllByUserId(Long userId);

    // 특정 유저 & 카테고리로 조회 (userId 기반)
    List<PlannerEntity> findAllByUserIdAndCategory(Long userId, Category category);

    // ✅ [추가] 특정 User 엔티티 + 카테고리 기반 조회 (Service에서 사용 중)
    List<PlannerEntity> findByUserAndCategory(User user, Category category);
}
