package com.example.likelion13thminihackathon.graduation;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "graduation_requirements")
public class GraduationRequirementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 어떤 학과에 대한 졸업요건인지
    @Column(nullable = false)
    private String departmentName;

    @Column(columnDefinition = "TEXT")
    private String additionalRequirements;

}
