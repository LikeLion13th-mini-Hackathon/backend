package com.example.likelion13thminihackathon.graduationMemo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GraduationMemoService {

    private final GraduationMemoRepository graduationMemoRepository;

    // 🔹 메모 저장
    @Transactional
    public void saveMemo(GraduationMemoDTO dto) {
        GraduationMemoEntity entity = GraduationMemoEntity.builder()
                .category(dto.getCategory())  // enum 타입
                .content(dto.getContent())
                .build();  // createdAt은 자동 생성됨

        graduationMemoRepository.save(entity);
    }

    // 🔹 카테고리 기준 메모 조회
    @Transactional(readOnly = true)
    public List<GraduationMemoDTO> getMemos(GraduationMemoCategory category) {
        return graduationMemoRepository.findAllByCategory(category)
                .stream()
                .map(entity -> GraduationMemoDTO.builder()
                        .category(entity.getCategory())
                        .content(entity.getContent())
                        .createdAt(entity.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
