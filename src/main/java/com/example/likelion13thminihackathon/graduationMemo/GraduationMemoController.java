package com.example.likelion13thminihackathon.graduationMemo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/graduation-memo")
@RequiredArgsConstructor
public class GraduationMemoController {

    private final GraduationMemoService graduationMemoService;

    // 🔹 메모 등록
    @PostMapping
    public ResponseEntity<String> saveMemo(@RequestBody GraduationMemoDTO dto) {
        graduationMemoService.saveMemo(dto);
        return ResponseEntity.ok("메모 등록 완료");
    }

    // 🔹 메모 조회 (카테고리별)
    @GetMapping
    public ResponseEntity<List<GraduationMemoDTO>> getMemos(@RequestParam String category) {
        GraduationMemoCategory enumCategory = GraduationMemoCategory.from(category); // 직접 변환
        List<GraduationMemoDTO> memos = graduationMemoService.getMemos(enumCategory);
        return ResponseEntity.ok(memos);
    }

    // 🔹 메모 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMemo(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String newContent = request.get("content");
        graduationMemoService.updateMemo(id, newContent);
        return ResponseEntity.ok("메모 수정 완료");
    }
}
