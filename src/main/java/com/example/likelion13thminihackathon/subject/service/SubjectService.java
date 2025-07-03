package com.example.likelion13thminihackathon.subject.service;

import com.example.likelion13thminihackathon.subject.dto.SubjectRequestDto;
import com.example.likelion13thminihackathon.subject.entity.Subject;
import com.example.likelion13thminihackathon.subject.repository.SubjectRepository;
import com.example.likelion13thminihackathon.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    // 과목 등록
    public void createSubject(SubjectRequestDto dto, User user) {
        Subject subject = Subject.builder()
                .semester(dto.getSemester())
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

        subject.setSemester(dto.getSemester());
        subject.setSubjectName(dto.getSubjectName());
        subject.setCredit(dto.getCredit());
        subject.setGrade(dto.getGrade());
    }
}
