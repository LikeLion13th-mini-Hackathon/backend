package com.example.likelion13thminihackathon.subjectMemo.service;

import com.example.likelion13thminihackathon.subjectMemo.dto.SubjectMemoRequestDto;
import com.example.likelion13thminihackathon.subjectMemo.dto.SubjectMemoResponseDto;
import com.example.likelion13thminihackathon.subject.entity.Subject;
import com.example.likelion13thminihackathon.subjectMemo.entity.SubjectMemo;
import com.example.likelion13thminihackathon.subjectMemo.repository.SubjectMemoRepository;
import com.example.likelion13thminihackathon.subject.repository.SubjectRepository;
import com.example.likelion13thminihackathon.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectMemoService {

    private final SubjectMemoRepository subjectMemoRepository;
    private final SubjectRepository subjectRepository;

    // 메모 등록 및 DTO 반환
    public SubjectMemoResponseDto createMemo(Long subjectId, SubjectMemoRequestDto requestDto, User user) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("과목을 찾을 수 없습니다."));

        // 본인 과목인지 확인
        if (!subject.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("본인의 과목에만 메모를 작성할 수 있습니다.");
        }

        // 이미 메모가 존재하는지 확인
        boolean exists = subjectMemoRepository.existsBySubjectIdAndUserId(subjectId, user.getId());
        if (exists) {
            throw new IllegalStateException("이미 이 과목에 대한 메모가 존재합니다.");
        }

        // 새 메모 저장
        SubjectMemo memo = SubjectMemo.builder()
                .memo(requestDto.getMemo())
                .subject(subject)
                .user(user)
                .build();

        subjectMemoRepository.save(memo);

        // 응답 DTO 생성 및 반환
        return SubjectMemoResponseDto.builder()
                .subjectId(subject.getId())
                .subjectName(subject.getSubjectName())
                .memo(memo.getMemo())
                .build();
    }
}
