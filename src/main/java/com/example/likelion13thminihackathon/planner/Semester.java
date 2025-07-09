package com.example.likelion13thminihackathon.planner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Semester {
    FIRST_YEAR_FIRST("1학년1학기"),
    FIRST_YEAR_SECOND("1학년2학기"),
    SECOND_YEAR_FIRST("2학년1학기"),
    SECOND_YEAR_SECOND("2학년2학기"),
    THIRD_YEAR_FIRST("3학년1학기"),
    THIRD_YEAR_SECOND("3학년2학기"),
    FOURTH_YEAR_FIRST("4학년1학기"),
    FOURTH_YEAR_SECOND("4학년2학기"),
    FIFTH_YEAR_FIRST("5학년 1학기"),
    FIFTH_YEAR_SECOND("5학년 2학기"),
    SIXTH_YEAR_FIRST("6학년 1학기"),
    SIXTH_YEAR_SECOND("6학년 2학기"),
    ETC("기타 학기");

    private final String label;

    Semester(String label) {
        this.label = label;
    }

    @JsonValue  // JSON 응답에 이 값 사용
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static Semester fromString(String value) {
        for (Semester semester : Semester.values()) {
            if (semester.name().equalsIgnoreCase(value) || semester.label.equals(value)) {
                return semester;
            }
        }
        throw new IllegalArgumentException("Unknown semester: " + value);
    }

}
