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

@Service
@RequiredArgsConstructor
public class PlannerService {

    private final PlannerRepository plannerRepository;
    private final UserRepository userRepository;

    // 플래너 등록
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

    // 플래너 수정
    public PlannerEntity updatePlan(Long id, PlannerDTO dto) {
        PlannerEntity existingPlan = plannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planner not found with id: " + id));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        if (!existingPlan.getUser().getEmail().equals(email)) {
            throw new RuntimeException("권한이 없습니다. 다른 사용자의 플래너를 수정할 수 없습니다.");
        }

        if (dto.getSemester() != null) existingPlan.setSemester(dto.getSemester());
        if (dto.getCategory() != null) existingPlan.setCategory(dto.getCategory());
        if (dto.getGoal() != null) existingPlan.setGoal(dto.getGoal());

        existingPlan.setUpdatedAt(LocalDateTime.now());
        if (dto.getDeletedAt() != null) existingPlan.setDeletedAt(dto.getDeletedAt());

        return plannerRepository.save(existingPlan);
    }

    // 단일 플래너 조회
    public PlannerDTO getPlanById(Long id) {
        PlannerEntity entity = plannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planner not found with id: " + id));

        PlannerDTO dto = new PlannerDTO();
        dto.setSemester(entity.getSemester());
        dto.setCategory(entity.getCategory());
        dto.setGoal(entity.getGoal());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setDeletedAt(entity.getDeletedAt());

        return dto;
    }

    // ✅ 카테고리로 전체 플래너 조회 (해당 사용자 기준)
    public List<PlannerDTO> getPlansByCategory(Category category) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        List<PlannerEntity> entities = plannerRepository.findByUserAndCategory(user, category);

        return entities.stream().map(entity -> {
            PlannerDTO dto = new PlannerDTO();
            dto.setSemester(entity.getSemester());
            dto.setCategory(entity.getCategory());
            dto.setGoal(entity.getGoal());
            dto.setCreatedAt(entity.getCreatedAt());
            dto.setUpdatedAt(entity.getUpdatedAt());
            dto.setDeletedAt(entity.getDeletedAt());
            return dto;
        }).collect(Collectors.toList());
    }

    // 플래너 삭제
    public void deletePlan(Long id) {
        PlannerEntity existingPlan = plannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planner not found with id: " + id));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        if (!existingPlan.getUser().getEmail().equals(email)) {
            throw new RuntimeException("권한이 없습니다. 다른 사용자의 플래너를 삭제할 수 없습니다.");
        }

        plannerRepository.deleteById(id);
    }
}
