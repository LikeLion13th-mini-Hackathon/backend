package com.example.likelion13thminihackathon.graduation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/graduation")
@RequiredArgsConstructor
public class GraduationRequirementController {

    private final GraduationRequirementService graduationRequirementService;

    // 🔹 특정 학과의 졸업요건 조회 (예: /api/graduation/department?name=컴퓨터공학부)
    @GetMapping("/department")
    public ResponseEntity<List<GraduationRequirementDTO>> getRequirementsByDepartment(@RequestParam String name) {
        String trimmedName = name.trim();  // 앞뒤 공백 제거
        List<GraduationRequirementDTO> requirements = graduationRequirementService.getRequirementsByDepartment(trimmedName);
        return ResponseEntity.ok(requirements);
    }


    // 🔹 전체 졸업요건 조회 (예: /api/graduation)
    @GetMapping
    public ResponseEntity<List<GraduationRequirementDTO>> getAllRequirements() {
        List<GraduationRequirementDTO> requirements = graduationRequirementService.getAllRequirements();
        return ResponseEntity.ok(requirements);
    }
}
