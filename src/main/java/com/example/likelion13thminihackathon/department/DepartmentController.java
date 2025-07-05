package com.example.likelion13thminihackathon.department;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    // 학과 등록
    @PostMapping
    public ResponseEntity<String> addDepartment(@RequestBody DepartmentDTO dto) {
        departmentService.addDepartment(dto);
        return ResponseEntity.ok("학과 등록 성공");
    }

    // 전체 학과 조회
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    // 특정 단과대학의 학과 목록 조회
    @GetMapping("/by-college")
    public ResponseEntity<List<DepartmentDTO>> getDepartmentsByCollege(@RequestParam Long collegeId) {
        return ResponseEntity.ok(departmentService.getDepartmentsByCollege(collegeId));
    }

    // 학과 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("학과 삭제 성공");
    }
}
