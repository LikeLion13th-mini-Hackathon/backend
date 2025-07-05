package com.example.likelion13thminihackathon.graduationMemo;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum GraduationMemoCategory {
    CREDIT("학점"),
    TOEIC("토익"),
    GRADUATION_PROJECT("졸업작품");

    private final String displayName;

    GraduationMemoCategory(String displayName) {
        this.displayName = displayName;
    }

    // 🔹 한글 → Enum 매핑
    @JsonCreator
    public static GraduationMemoCategory from(String input) {
        return Stream.of(GraduationMemoCategory.values())
                .filter(c -> c.name().equalsIgnoreCase(input) || c.displayName.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 카테고리입니다: " + input));
    }
}
