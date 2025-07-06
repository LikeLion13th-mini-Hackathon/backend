package com.example.likelion13thminihackathon.planner;

import com.example.likelion13thminihackathon.user.entity.User;
import com.example.likelion13thminihackathon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// ... 생략된 import 및 어노테이션

// 생략된 import ...

@Service
@RequiredArgsConstructor
public class PlannerService {

    private final PlannerRepository plannerRepository;
    private final UserRepository userRepository;

    public PlannerEntity addPlan(PlannerDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

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

    public PlannerEntity updatePlan(Long id, PlannerDTO dto) {
        PlannerEntity existingPlan = plannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planner not found with id: " + id));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        if (!existingPlan.getUser().getEmail().equals(email)) {
            throw new RuntimeException("권한이 없습니다.");
        }

        if (dto.getSemester() != null) existingPlan.setSemester(dto.getSemester());
        if (dto.getCategory() != null) existingPlan.setCategory(dto.getCategory());
        if (dto.getGoal() != null) existingPlan.setGoal(dto.getGoal());
        if (dto.getDeletedAt() != null) existingPlan.setDeletedAt(dto.getDeletedAt());

        existingPlan.setUpdatedAt(LocalDateTime.now());

        return plannerRepository.save(existingPlan);
    }

    public PlannerDTO getPlanById(Long id) {
        PlannerEntity entity = plannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planner not found with id: " + id));

        PlannerDTO dto = new PlannerDTO();
        dto.setId(entity.getId());
        dto.setSemester(entity.getSemester());
        dto.setCategory(entity.getCategory());
        dto.setGoal(entity.getGoal());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setDeletedAt(entity.getDeletedAt());

        return dto;
    }

    public List<PlannerDTO> getPlansByCategory(Category category) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        List<PlannerEntity> entities = plannerRepository.findByUserAndCategory(user, category);

        return entities.stream().map(entity -> {
            PlannerDTO dto = new PlannerDTO();
            dto.setId(entity.getId());
            dto.setSemester(entity.getSemester());
            dto.setCategory(entity.getCategory());
            dto.setGoal(entity.getGoal());
            dto.setCreatedAt(entity.getCreatedAt());
            dto.setUpdatedAt(entity.getUpdatedAt());
            dto.setDeletedAt(entity.getDeletedAt());
            return dto;
        }).collect(Collectors.toList());
    }

    // ✅ 학기 + 카테고리로 조회
    public List<PlannerDTO> getPlansByCategoryAndSemester(Category category, Semester semester) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        List<PlannerEntity> entities = plannerRepository.findByUserAndCategoryAndSemester(user, category, semester);

        return entities.stream().map(entity -> {
            PlannerDTO dto = new PlannerDTO();
            dto.setId(entity.getId());
            dto.setSemester(entity.getSemester());
            dto.setCategory(entity.getCategory());
            dto.setGoal(entity.getGoal());
            dto.setCreatedAt(entity.getCreatedAt());
            dto.setUpdatedAt(entity.getUpdatedAt());
            dto.setDeletedAt(entity.getDeletedAt());
            return dto;
        }).collect(Collectors.toList());
    }

    public void deletePlan(Long id) {
        PlannerEntity existingPlan = plannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planner not found with id: " + id));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        if (!existingPlan.getUser().getEmail().equals(email)) {
            throw new RuntimeException("권한이 없습니다.");
        }

        plannerRepository.deleteById(id);
    }

    public List<PlannerDTO> getPlansBySemester(Semester semester) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        List<PlannerEntity> entities = plannerRepository.findByUserAndSemester(user, semester);

        return entities.stream().map(entity -> {
            PlannerDTO dto = new PlannerDTO();
            dto.setId(entity.getId());  // ✅ plannerId 포함
            dto.setSemester(entity.getSemester());
            dto.setCategory(entity.getCategory());
            dto.setGoal(entity.getGoal());
            dto.setCreatedAt(entity.getCreatedAt());
            dto.setUpdatedAt(entity.getUpdatedAt());
            dto.setDeletedAt(entity.getDeletedAt());
            return dto;
        }).collect(Collectors.toList());
    }


}
