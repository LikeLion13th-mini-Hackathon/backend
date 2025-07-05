package com.example.likelion13thminihackathon.college;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/colleges")
@RequiredArgsConstructor
public class CollegeController {

    private final CollegeService collegeService;

    // ✅ 단과대학 전체 목록 조회
    @GetMapping
    public ResponseEntity<?> getAllColleges() {
        List<CollegeEntity> colleges = collegeService.getAllColleges();

        return ResponseEntity.ok().body(
                new HashMap<>() {{
                    put("message", "단과대학 목록 조회 성공");
                    put("data", colleges);
                }}
        );
    }

}
