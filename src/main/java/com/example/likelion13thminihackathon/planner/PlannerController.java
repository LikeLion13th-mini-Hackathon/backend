package com.example.likelion13thminihackathon.planner;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
}
