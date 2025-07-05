package com.example.likelion13thminihackathon.department;

import com.example.likelion13thminihackathon.college.CollegeEntity;
import com.example.likelion13thminihackathon.college.CollegeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DepartmentDataInitializer {

    private final DepartmentRepository departmentRepository;
    private final CollegeRepository collegeRepository;

    @PostConstruct
    public void init() {
        // 인문대학
        addDepartment("국어국문학과", "인문대학");
        addDepartment("영어영문학과", "인문대학");
        addDepartment("독어독문학과", "인문대학");
        addDepartment("불어불문학과", "인문대학");
        addDepartment("일본지역문화학과", "인문대학");
        addDepartment("중어중국학과", "인문대학");

        // 자연과학대학
        addDepartment("수학과", "자연과학대학");
        addDepartment("물리학과", "자연과학대학");
        addDepartment("화학과", "자연과학대학");
        addDepartment("패션산업학과", "자연과학대학");
        addDepartment("해양학과", "자연과학대학");

        // 사회과학대학
        addDepartment("사회복지학과", "사회과학대학");
        addDepartment("미디어커뮤니케이션학과", "사회과학대학");
        addDepartment("문헌정보학과", "사회과학대학");
        addDepartment("창의인재개발학과", "사회과학대학");

        // 글로벌정경대학
        addDepartment("행정학과", "글로벌정경대학");
        addDepartment("정치외교학과", "글로벌정경대학");
        addDepartment("경제학과", "글로벌정경대학");
        addDepartment("무역학과", "글로벌정경대학");
        addDepartment("소비자학과", "글로벌정경대학");

        // 공과대학
        addDepartment("기계공학과", "공과대학");
        addDepartment("전기공학과", "공과대학");
        addDepartment("전자공학과", "공과대학");
        addDepartment("산업경영공학과", "공과대학");
        addDepartment("신소재공학과", "공과대학");
        addDepartment("안전공학과", "공과대학");
        addDepartment("에너지화학공학과", "공과대학");
        addDepartment("바이오-로봇 시스템 공학과", "공과대학");

        // 정보기술대학
        addDepartment("컴퓨터공학부", "정보기술대학");
        addDepartment("정보통신공학과", "정보기술대학");
        addDepartment("임베디드시스템공학과", "정보기술대학");

        // 경영대학
        addDepartment("경영학부", "경영대학");
        addDepartment("데이터과학과", "경영대학");
        addDepartment("세무회계학과", "경영대학");
        addDepartment("테크노경영학과", "경영대학");

        // 예술체육대학
        addDepartment("한국화전공(조형예술학부)", "예술체육대학");
        addDepartment("서양화전공(조형예술학부)", "예술체육대학");
        addDepartment("디자인학부", "예술체육대학");
        addDepartment("공연예술학과", "예술체육대학");
        addDepartment("스포츠과학부", "예술체육대학");
        addDepartment("운동건강학부", "예술체육대학");

        // 사범대학
        addDepartment("국어교육과", "사범대학");
        addDepartment("영어교육과", "사범대학");
        addDepartment("일어교육과", "사범대학");
        addDepartment("수학교육과", "사범대학");
        addDepartment("체육교육과", "사범대학");
        addDepartment("유아교육과", "사범대학");
        addDepartment("역사교육과", "사범대학");
        addDepartment("윤리교육과", "사범대학");

        // 도시과학대학
        addDepartment("도시행정학과", "도시과학대학");
        addDepartment("건설환경공학", "도시과학대학");
        addDepartment("환경공학", "도시과학대학");
        addDepartment("도시공학과", "도시과학대학");
        addDepartment("건축공학", "도시과학대학");
        addDepartment("도시건축학", "도시과학대학");

        // 생명과학기술대학
        addDepartment("생명과학부(생명과학전공)", "생명과학기술대학");
        addDepartment("생명과학부(분자의생명전공)", "생명과학기술대학");
        addDepartment("생명공학부(생명공학전공)", "생명과학기술대학");
        addDepartment("생명공학부(나노바이오공학전공)", "생명과학기술대학");

        // 융합자유전공대학
        addDepartment("자유전공학부", "융합자유전공대학");
        addDepartment("국제자유전공학부", "융합자유전공대학");
        addDepartment("융합학부", "융합자유전공대학");

        // 동북아국제통상학부
        addDepartment("동북아국제통상전공", "동북아국제통상학부");
        addDepartment("스마트물류공학전공", "동북아국제통상학부");
        addDepartment("IBE 전공", "동북아국제통상학부");

        // 법학부
        addDepartment("법학과", "법학부");
    }

    private void addDepartment(String departmentName, String collegeName) {
        Optional<CollegeEntity> collegeOpt = collegeRepository.findByName(collegeName);
        if (collegeOpt.isPresent()) {
            CollegeEntity college = collegeOpt.get();

            boolean exists = departmentRepository.findByCollege(college).stream()
                    .anyMatch(dep -> dep.getName().equals(departmentName));

            if (!exists) {
                DepartmentEntity department = DepartmentEntity.builder()
                        .name(departmentName)
                        .college(college)
                        .build();
                departmentRepository.save(department);
            }
        }
    }
}
