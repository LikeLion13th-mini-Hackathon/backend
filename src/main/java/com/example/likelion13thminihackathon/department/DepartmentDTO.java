package com.example.likelion13thminihackathon.department;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDTO {

    private Long id;         // 학과 ID (조회 시 사용)
    private String name;     // 학과 이름
    private Long collegeId;  // 소속 단과대학 ID
}
