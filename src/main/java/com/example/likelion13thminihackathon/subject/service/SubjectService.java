package com.example.likelion13thminihackathon.subject.service;

import com.example.likelion13thminihackathon.subject.dto.SubjectRequestDto;
import com.example.likelion13thminihackathon.subject.entity.Subject;
import com.example.likelion13thminihackathon.subject.repository.SubjectRepository;
import com.example.likelion13thminihackathon.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.likelion13thminihackathon.subject.dto.SubjectResponseDto;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    // 과목 등록
    public void createSubject(SubjectRequestDto dto, User user) {
        Subject subject = Subject.builder()
                .gradeLevel(dto.getGradeLevel()) // 학년
                .semester(dto.getSemester()) // 학기
                .subjectName(dto.getSubjectName())
                .credit(dto.getCredit())
                .grade(dto.getGrade())
                .user(user)
                .build();

        subjectRepository.save(subject);
    }

    // 과목 수정
    @Transactional
    public void updateSubject(Long id, SubjectRequestDto dto, User user) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 과목이 존재하지 않습니다."));

        if (!subject.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("본인이 등록한 과목만 수정할 수 있습니다.");
        }

        subject.setGradeLevel(dto.getGradeLevel()); // 학년
        subject.setSemester(dto.getSemester()); // 학기
        subject.setSubjectName(dto.getSubjectName());
        subject.setCredit(dto.getCredit());
        subject.setGrade(dto.getGrade());
    }

    // 학기별 과목 전체 조회
    public List<SubjectResponseDto> getSubjectsBySemester(User user, Integer gradeLevel, Integer semester) {
        List<Subject> subjects = subjectRepository
                .findByUserIdAndGradeLevelAndSemester(user.getId(), gradeLevel, semester);

        return subjects.stream()
                .map(SubjectResponseDto::new)
                .collect(Collectors.toList());
    }
}
