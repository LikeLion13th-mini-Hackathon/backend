package com.example.likelion13thminihackathon.planner;

import com.example.likelion13thminihackathon.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlannerRepository extends JpaRepository<PlannerEntity, Long> {

    List<PlannerEntity> findAllByUserId(Long userId);

    List<PlannerEntity> findAllByUserIdAndCategory(Long userId, Category category);

    List<PlannerEntity> findByUserAndCategory(User user, Category category);

    // ✅ 학기(Enum) 추가된 조회
    List<PlannerEntity> findByUserAndCategoryAndSemester(User user, Category category, Semester semester);

    List<PlannerEntity> findByUserAndSemester(User user, Semester semester);

}
