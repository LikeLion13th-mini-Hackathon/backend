package com.example.likelion13thminihackathon.planner;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        PlannerDTO plannerDTO = plannerService.getPlanById(id);
        return ResponseEntity.ok(plannerDTO);
    }

    // ✅ [추가] 카테고리 기반 전체 플래너 조회
    @GetMapping("/category")
    public ResponseEntity<List<PlannerDTO>> getPlansByCategory(@RequestParam Category category) {
        List<PlannerDTO> plans = plannerService.getPlansByCategory(category);
        return ResponseEntity.ok(plans);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletePlan(@PathVariable Long id) {
        plannerService.deletePlan(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "플래너 삭제 성공");
        return ResponseEntity.ok(response);
    }
}
