package com.example.likelion13thminihackathon.planner;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/planner")
@RequiredArgsConstructor
public class PlannerController {

    private final PlannerService plannerService;

    @PostMapping
    public ResponseEntity<Map<String, String>> addPlan(@RequestBody PlannerDTO plannerDTO) {
        plannerService.addPlan(plannerDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", "플래너 등록 성공");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updatePlan(@PathVariable Long id, @RequestBody PlannerDTO plannerDTO) {
        plannerService.updatePlan(id, plannerDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", "플래너 수정 성공");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PlannerDTO> getPlan(@RequestParam Long id) {
        return ResponseEntity.ok(plannerService.getPlanById(id));
    }

    @GetMapping("/category")
    public ResponseEntity<List<PlannerDTO>> getPlansByCategory(@RequestParam Category category) {
        return ResponseEntity.ok(plannerService.getPlansByCategory(category));
    }

    // ✅ 학기도 Enum으로 받음 (e.g. "1학년1학기")
    @GetMapping("/category-semester")
    public ResponseEntity<List<PlannerDTO>> getPlansByCategoryAndSemester(
            @RequestParam Category category,
            @RequestParam Semester semester) {
        return ResponseEntity.ok(plannerService.getPlansByCategoryAndSemester(category, semester));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletePlan(@PathVariable Long id) {
        plannerService.deletePlan(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "플래너 삭제 성공");
        return ResponseEntity.ok(response);
    }

    // ✅ 학기 목록 전체 반환 API
    @GetMapping("/semesters")
    public ResponseEntity<List<String>> getAllSemesters() {
        List<String> semesters = Arrays.stream(Semester.values())
                .map(Semester::getLabel) // 한글 표시값
                .toList();
        return ResponseEntity.ok(semesters);
    }

    // ✅ 학기 기준 전체 플래너 조회
    @GetMapping("/semester")
    public ResponseEntity<List<PlannerDTO>> getPlansBySemester(@RequestParam Semester semester) {
        return ResponseEntity.ok(plannerService.getPlansBySemester(semester));
    }


}
