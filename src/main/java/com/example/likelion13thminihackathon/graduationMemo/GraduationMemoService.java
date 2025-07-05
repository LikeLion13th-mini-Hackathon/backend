package com.example.likelion13thminihackathon.graduationMemo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GraduationMemoService {

    private final GraduationMemoRepository graduationMemoRepository;

    // 🔹 메모 저장
    @Transactional
    public void saveMemo(GraduationMemoDTO dto) {
        GraduationMemoEntity entity = GraduationMemoEntity.builder()
                .category(dto.getCategory())
                .content(dto.getContent())
                .build();  // createdAt은 자동 생성됨

        graduationMemoRepository.save(entity);
    }
}
