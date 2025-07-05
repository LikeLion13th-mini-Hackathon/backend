package com.example.likelion13thminihackathon.college;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollegeService {

    private final CollegeRepository collegeRepository;

    // 단과대학 전체 목록 조회
    public List<CollegeEntity> getAllColleges() {
        return collegeRepository.findAll();
    }
}
