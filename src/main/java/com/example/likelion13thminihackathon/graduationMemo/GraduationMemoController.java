package com.example.likelion13thminihackathon.graduationMemo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/graduation-memo")
@RequiredArgsConstructor
public class GraduationMemoController {

    private final GraduationMemoService graduationMemoService;

    // 메모 등록
    @PostMapping
    public ResponseEntity<String> saveMemo(@RequestBody GraduationMemoDTO dto) {
        graduationMemoService.saveMemo(dto);
        return ResponseEntity.ok("메모 등록 완료");
    }
}
