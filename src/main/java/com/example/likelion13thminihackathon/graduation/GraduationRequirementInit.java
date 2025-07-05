package com.example.likelion13thminihackathon.graduation;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GraduationRequirementInit implements CommandLineRunner {

    private final GraduationRequirementRepository requirementRepository;

    @Override
    public void run(String... args) {
        // ✅ 이미 저장된 졸업요건이 있다면 초기화 생략
        if (requirementRepository.count() > 0) return;

        // 영어영문학과
        requirementRepository.save(
                GraduationRequirementEntity.builder()
                        .departmentName("영어영문학과")
                        .additionalRequirements("""
                            ✅ 졸업논문 제출:
                            - 졸업논문지도신청서를 바탕으로 논문 작성 후, 지도교수님의 최종 제출 허가를 받아 학과 제출
                            - 논문 심사 결과 D 이상인 경우 논문 합격
                            - 제출 마감 기한은 매 학기 학부게시판 별도 공지

                            ✅ 영어졸업인증 기준:
                            - 2010학년도 이후 입학자:
                              TOEIC 800 / TOEFL(iBT) 96 / New TEPS 309 / IELTS 7 / SPEAKING 140 / WRITING 150 / OPIc IH
                            - 2009학년도 이전 입학자:
                              TOEIC 600 / TOEFL(iBT) 68 / New TEPS 227 / IELTS 5.5 / SPEAKING 110 / WRITING 120 / OPIc IL
                            """)
                        .build()
        );

        // 디자인학부
        requirementRepository.save(
                GraduationRequirementEntity.builder()
                        .departmentName("디자인학부")
                        .additionalRequirements("""
                            ✅ 졸업논문:
                            - 졸업논문은 실기발표로 공개발표로 되어야 하며 심사평가 합격기준은 D급 이상

                            ✅ 영어졸업인증제:
                            - 2010학년도 이후 입학자 및 해당 학년으로 편입학 한 자
                            - TOEIC 990 / TOEFL(iBT) 120 / New TEPS 990 / IELTS 9 / SPEAKING 200 / WRITING 200 / OPIc AD
                            - 예술체육대학, 야간학과(부): TOEIC 600 / TOEFL(iBT) 68 / New TEPS 257 / IELTS 5.5 / SPEAKING 110 / WRITING 120 / OPIc IL
                            """)
                        .build()
        );

        // 산업경영공학과
        requirementRepository.save(
                GraduationRequirementEntity.builder()
                        .departmentName("산업경영공학과")
                        .additionalRequirements("""
                            ✅ 졸업논문: 실기발표, D급 이상

                            ✅ 영어졸업인증:
                            - TOEIC 990 / TOEFL(iBT) 120 / NEW TEPS 990 / IELTS 9 / SPEAKING 200 / WRITING 200 / OPIc AD
                            - 예술체육대학, 야간학과(부): TOEIC 600 / TOEFL(iBT) 68 / NEW TEPS 257 / IELTS 5.5 / SPEAKING 110 / WRITING 120 / OPIc IL

                            ✅ 졸업학점:
                            - ~2019학번: 140
                            - 2020~2022: 137
                            - 2023~: 130
                            - 전공: 72 이상

                            ✅ 교양:
                            - 2023 이후: 핵심교양 6영역 중 3과목, Academic English, 회화, SW 각 2학점
                            - 공학윤리, 기초데이터분석 필수
                            """)
                        .build()
        );

        // 컴퓨터공학부
        requirementRepository.save(
                GraduationRequirementEntity.builder()
                        .departmentName("컴퓨터공학부")
                        .additionalRequirements("""
                            ✅ 전공학점:
                            - 2020학번 이후: 전공기초 포함 72학점 이상

                            ✅ 영어졸업인증제:
                            - 정보기술대학: TOEIC 700 / TOEFL(iBT) 82 / New TEPS 264 / IELTS 6.5 / SPEAKING 130 / WRITING 140 / OPIc IM
                            - 야간학과(부): TOEIC 600 / TOEFL(iBT) 68 / New TEPS 227 / IELTS 5.5 / SPEAKING 110 / WRITING 120 / OPIc IL

                            ✅ 졸업작품 발표: 3인 이상 교수 심사에서 D 이상

                            ✅ 대체 인정: 본교 아학원 특강 96시간 이수 (최근 2년 내 공인영어시험 2회 응시자)

                            ✅ 조기졸업 요건: 4학기까지 평점 4.0 이상 → 6~7학기 유지 시 조기졸업 가능
                            """)
                        .build()
        );
    }
}
