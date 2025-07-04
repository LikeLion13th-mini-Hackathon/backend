package com.example.likelion13thminihackathon.subjectMemo.service;

import com.example.likelion13thminihackathon.subject.entity.Subject;
import com.example.likelion13thminihackathon.subject.repository.SubjectRepository;
import com.example.likelion13thminihackathon.subjectMemo.dto.SubjectMemoRequestDto;
import com.example.likelion13thminihackathon.subjectMemo.dto.SubjectMemoResponseDto;
import com.example.likelion13thminihackathon.subjectMemo.entity.SubjectMemo;
import com.example.likelion13thminihackathon.subjectMemo.repository.SubjectMemoRepository;
import com.example.likelion13thminihackathon.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubjectMemoService {

    private final SubjectMemoRepository subjectMemoRepository;
    private final SubjectRepository subjectRepository;

    // 메모 생성
    @Transactional
    public SubjectMemoResponseDto saveMemo(Long subjectId, SubjectMemoRequestDto requestDto, User user) {
        // 1) 과목 존재 및 본인 확인
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("과목을 찾을 수 없습니다."));
        if (!subject.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("본인의 과목에만 메모를 작성할 수 있습니다.");
        }

        // 2) 기존 메모가 있으면 꺼내오고, 없으면 새로 빌드
        SubjectMemo memo = subjectMemoRepository
                .findBySubjectIdAndUserId(subjectId, user.getId())
                .orElseGet(() ->
                        SubjectMemo.builder()
                                .user(user)
                                .subject(subject)
                                .build()
                );

        // 3) 언제나 덮어쓰기
        memo.setMemo(requestDto.getMemo());
        SubjectMemo saved = subjectMemoRepository.save(memo);

        // 4) 응답 DTO
        return SubjectMemoResponseDto.builder()
                .subjectId(subject.getId())
                .subjectName(subject.getSubjectName())
                .memo(saved.getMemo())
                .build();
    }

    /**
     * 메모 조회 (신규면 빈 문자열로)
     */
    @Transactional(readOnly = true)
    public SubjectMemoResponseDto getMemo(Long subjectId, User user) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("과목을 찾을 수 없습니다."));
        if (!subject.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("본인의 과목만 조회할 수 있습니다.");
        }

        String memoText = subjectMemoRepository
                .findBySubjectIdAndUserId(subjectId, user.getId())
                .map(SubjectMemo::getMemo)
                .orElse("");

        return SubjectMemoResponseDto.builder()
                .subjectId(subject.getId())
                .subjectName(subject.getSubjectName())
                .memo(memoText)
                .build();
    }


    @Transactional
    public void deleteMemo(Long subjectId, User user) {
        SubjectMemo memo = subjectMemoRepository
                .findBySubjectIdAndUserId(subjectId, user.getId())
                .orElseThrow(() -> new RuntimeException("삭제할 메모가 없습니다."));
        subjectMemoRepository.delete(memo);
    }
}
