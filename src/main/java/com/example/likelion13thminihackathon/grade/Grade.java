package com.example.likelion13thminihackathon.grade;

public enum Grade {
    A_PLUS("A+", 4.5),
    A("A", 4.0),
    B_PLUS("B+", 3.5),
    B("B", 3.0),
    C_PLUS("C+", 2.5),
    C("C", 2.0),
    D_PLUS("D+", 1.5),
    D("D", 1.0),
    F("F", 0.0),
    P("P", null),      // Pass - 평점 계산 제외
    NP("NP", null);    // No Pass - 평점 계산 제외

    private final String display;
    private final Double score;

    Grade(String display, Double score) {
        this.display = display;
        this.score = score;
    }

    public String getDisplay() {
        return display;
    }

    public Double getScore() {
        return score;
    }
}
