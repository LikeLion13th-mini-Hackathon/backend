package com.example.likelion13thminihackathon.department;

import com.example.likelion13thminihackathon.college.CollegeEntity;
import com.example.likelion13thminihackathon.college.CollegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final CollegeRepository collegeRepository;

    // 학과 등록
    public void addDepartment(DepartmentDTO dto) {
        CollegeEntity college = collegeRepository.findById(dto.getCollegeId())
                .orElseThrow(() -> new IllegalArgumentException("단과대학 ID가 존재하지 않습니다."));

        DepartmentEntity entity = DepartmentEntity.builder()
                .name(dto.getName())
                .college(college)
                .build();

        departmentRepository.save(entity);
    }

    // 전체 학과 조회
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 특정 단과대학의 학과 조회
    public List<DepartmentDTO> getDepartmentsByCollege(Long collegeId) {
        CollegeEntity college = collegeRepository.findById(collegeId)
                .orElseThrow(() -> new IllegalArgumentException("단과대학 ID가 존재하지 않습니다."));

        return departmentRepository.findByCollege(college).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 학과 삭제
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    // Entity → DTO 변환
    private DepartmentDTO convertToDTO(DepartmentEntity entity) {
        return DepartmentDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .collegeId(entity.getCollege().getId())
                .build();
    }
}
