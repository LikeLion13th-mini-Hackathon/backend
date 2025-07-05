package com.example.likelion13thminihackathon.subject.service;

import com.example.likelion13thminihackathon.subject.dto.SubjectRequestDto;
import com.example.likelion13thminihackathon.subject.dto.SubjectResponseDto;
import com.example.likelion13thminihackathon.subject.entity.Subject;
import com.example.likelion13thminihackathon.subject.repository.SubjectRepository;
import com.example.likelion13thminihackathon.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    // 개별 과목 수정 (업데이트 방식)
    @Transactional
    public void updateSubject(User user, Long subjectId, SubjectRequestDto dto) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("과목을 찾을 수 없습니다."));

        if (!subject.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("해당 과목에 대한 수정 권한이 없습니다.");
        }

        if (dto.getSubjectName() != null) {
            subject.setSubjectName(dto.getSubjectName());
        }
        if (dto.getCredit() != null) {
            subject.setCredit(dto.getCredit());
        }
        if (dto.getGrade() != null) {
            subject.setGrade(dto.getGrade());
        }
    }

    // 학기별 과목 전체 조회 + 없으면 8개 자동 생성
    @Transactional
    public List<SubjectResponseDto> getSubjectsBySemester(User user, Integer gradeLevel, Integer semester) {
        List<Subject> subjects = subjectRepository
                .findByUserIdAndGradeLevelAndSemester(user.getId(), gradeLevel, semester);

        // 과목 없으면 8개 자동 생성
        if (subjects.isEmpty()) {
            for (int i = 0; i < 8; i++) {
                Subject subject = Subject.builder()
                        .user(user)
                        .gradeLevel(gradeLevel)
                        .semester(semester)
                        .subjectName(null)
                        .credit(null)
                        .grade(null)
                        .build();
                subjectRepository.save(subject);
            }

            // 다시 조회
            subjects = subjectRepository
                    .findByUserIdAndGradeLevelAndSemester(user.getId(), gradeLevel, semester);
        }

        return subjects.stream()
                .map(SubjectResponseDto::new)
                .collect(Collectors.toList());
    }
}
