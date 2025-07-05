package com.example.likelion13thminihackathon.college;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CollegeDataInitializer implements CommandLineRunner {

    private final CollegeRepository collegeRepository;

    @Override
    public void run(String... args) throws Exception {
        if (collegeRepository.count() == 0) {
            List<CollegeEntity> colleges = List.of(
                    CollegeEntity.builder().name("경영대학").build(),
                    CollegeEntity.builder().name("공과대학").build(),
                    CollegeEntity.builder().name("글로벌정경대학").build(),
                    CollegeEntity.builder().name("도시과학대학").build(),
                    CollegeEntity.builder().name("동북아국제통상물류학부").build(),
                    CollegeEntity.builder().name("법학부").build(),
                    CollegeEntity.builder().name("사범대학").build(),
                    CollegeEntity.builder().name("사회과학대학").build(),
                    CollegeEntity.builder().name("생활과학기술대학").build(),
                    CollegeEntity.builder().name("예술체육대학").build(),
                    CollegeEntity.builder().name("융합자유전공대학").build(),
                    CollegeEntity.builder().name("자연과학대학").build(),
                    CollegeEntity.builder().name("정보기술대학").build(),
                    CollegeEntity.builder().name("인문대학").build()
            );
            collegeRepository.saveAll(colleges);
            System.out.println("✅ 단과대학 초기 데이터 입력 완료");
        } else {
            System.out.println("ℹ️ 단과대학 데이터 이미 존재함");
        }
    }
}
