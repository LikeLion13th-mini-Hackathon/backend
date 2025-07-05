package com.example.likelion13thminihackathon.graduation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GraduationRequirementService {

    private final GraduationRequirementRepository graduationRequirementRepository;

    // ✅ 학과 이름으로 졸업요건 조회 (트랜잭션 추가)
    @Transactional(readOnly = true)
    public List<GraduationRequirementDTO> getRequirementsByDepartment(String departmentName) {
        List<GraduationRequirementEntity> entities =
                graduationRequirementRepository.findByDepartmentNameIgnoreCaseAndTrimmed(departmentName);
        return convertToDTOList(entities);
    }

    // ✅ 전체 졸업요건 조회 (트랜잭션 추가)
    @Transactional(readOnly = true)
    public List<GraduationRequirementDTO> getAllRequirements() {
        List<GraduationRequirementEntity> entities = graduationRequirementRepository.findAll();
        return convertToDTOList(entities);
    }

    // ✅ Entity → DTO 변환 메서드
    private List<GraduationRequirementDTO> convertToDTOList(List<GraduationRequirementEntity> entities) {
        return entities.stream().map(requirement -> GraduationRequirementDTO.builder()
                .departmentName(requirement.getDepartmentName())
                .additionalRequirements(requirement.getAdditionalRequirements())
                .build()
        ).collect(Collectors.toList());
    }
}
