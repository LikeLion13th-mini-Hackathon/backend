package com.example.likelion13thminihackathon.achievement.repository;

import com.example.likelion13thminihackathon.achievement.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    Optional<Achievement> findByUserId(Long userId);
}
