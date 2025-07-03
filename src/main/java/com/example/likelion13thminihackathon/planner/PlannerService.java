package com.example.likelion13thminihackathon.planner;

import com.example.likelion13thminihackathon.user.entity.User;
import com.example.likelion13thminihackathon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PlannerService {

    private final PlannerRepository plannerRepository;
    private final UserRepository userRepository;

    public PlannerEntity addPlan(PlannerDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));

        PlannerEntity plannerEntity = PlannerEntity.builder()
                .user(user)
                .semester(dto.getSemester())
                .category(dto.getCategory())
                .goal(dto.getGoal())
                .createdAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDateTime.now())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt() != null ? dto.getDeletedAt() : false)
                .build();

        return plannerRepository.save(plannerEntity);
    }
}
