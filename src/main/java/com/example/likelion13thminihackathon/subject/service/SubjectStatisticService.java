package com.example.likelion13thminihackathon.subject.service;

import com.example.likelion13thminihackathon.subject.dto.SubjectStatisticResponseDto;
import com.example.likelion13thminihackathon.subject.entity.Subject;
import com.example.likelion13thminihackathon.subject.repository.SubjectRepository;
import com.example.likelion13thminihackathon.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SubjectStatisticService {

    private final SubjectRepository subjectRepository;

    // 성적별 점수 매핑 (P, NP는 GPA 계산에서 제외되므로 포함 안 함)
    private static final Map<String, Double> gradeToPoint = Map.ofEntries(
            Map.entry("A+", 4.5), Map.entry("A", 4.0), Map.entry("A0", 4.0),
            Map.entry("B+", 3.5), Map.entry("B", 3.0), Map.entry("B0", 3.0),
            Map.entry("C+", 2.5), Map.entry("C", 2.0), Map.entry("C0", 2.0),
            Map.entry("D+", 1.5), Map.entry("D", 1.0), Map.entry("D0", 1.0),
            Map.entry("F", 0.0)
    );

    public SubjectStatisticResponseDto calculateAll(User user) {
        List<Subject> subjects = subjectRepository.findAll().stream()
                .filter(s -> s.getUser().getId().equals(user.getId()))
                .toList();

        double totalScore = 0;
        int totalCreditForGPA = 0;
        int acquiredCredit = 0;

        for (Subject subject : subjects) {
            if (subject.getCredit() == null || subject.getGrade() == null) continue;

            String grade = subject.getGrade();
            int credit = subject.getCredit();

            // 취득학점 계산: P는 포함, NP와 F는 제외
            if (!grade.equals("F") && !grade.equals("NP")) {
                acquiredCredit += credit;
            }

            // GPA 계산: P와 NP는 제외
            if (gradeToPoint.containsKey(grade)) {
                double point = gradeToPoint.get(grade);
                totalScore += point * credit;
                totalCreditForGPA += credit;
            }
        }

        double gpa = totalCreditForGPA == 0 ? 0.0 : totalScore / totalCreditForGPA;
        return new SubjectStatisticResponseDto(round(gpa), acquiredCredit);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
