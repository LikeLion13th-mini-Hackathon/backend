package com.example.likelion13thminihackathon.graduation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface GraduationRequirementRepository extends JpaRepository<GraduationRequirementEntity, Long> {

    // 🔍 대소문자 무시 + 공백 제거 후 학과명 비교 (JPQL 사용)
    @Query("""
        SELECT g FROM GraduationRequirementEntity g
        WHERE TRIM(LOWER(g.departmentName)) = TRIM(LOWER(:name))
    """)
    List<GraduationRequirementEntity> findByDepartmentNameIgnoreCaseAndTrimmed(@Param("name") String name);
}
