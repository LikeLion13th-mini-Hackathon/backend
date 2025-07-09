package com.example.likelion13thminihackathon.achievement.service;

import com.example.likelion13thminihackathon.achievement.dto.AchievementRequestDto;
import com.example.likelion13thminihackathon.achievement.dto.AchievementResponseDto;
import com.example.likelion13thminihackathon.achievement.entity.Achievement;
import com.example.likelion13thminihackathon.achievement.repository.AchievementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AchievementService {

    private final AchievementRepository repository;

    public AchievementResponseDto saveOrUpdate(Long userId, AchievementRequestDto dto) {
        Achievement achievement = repository.findByUserId(userId)
                .map(a -> {
                    a.setTotalSemester(dto.getTotalSemester());
                    a.setCurrentSemester(dto.getTakenSemester());
                    return a;
                })
                .orElseGet(() -> Achievement.builder()
                        .userId(userId)
                        .totalSemester(dto.getTotalSemester())
                        .currentSemester(dto.getTakenSemester())
                        .build());

        repository.save(achievement);

        return toResponse(achievement);
    }

    public AchievementResponseDto getByUser(Long userId) {
        Achievement achievement = repository.findByUserId(userId)
                .orElse(
                        Achievement.builder()
                                .userId(userId)
                                .totalSemester(0)
                                .currentSemester(0)
                                .build()
                );
        return toResponse(achievement);
    }

    private AchievementResponseDto toResponse(Achievement achievement) {
        int rate = (achievement.getTotalSemester() == 0) ? 0 :
                (int) ((double) achievement.getCurrentSemester() / achievement.getTotalSemester() * 100);
        int remaining = achievement.getTotalSemester() - achievement.getCurrentSemester();
        String message = getMessageByRate(rate);

        return AchievementResponseDto.builder()
                .totalSemester(achievement.getTotalSemester())
                .currentSemester(achievement.getCurrentSemester())
                .achievementPercent(rate)
                .remainingSemester(remaining)
                .message(message)
                .build();
    }

    private String getMessageByRate(int rate) {
        if (rate == 0)
            return "드디어 시작이네! 여기서부터 너만의 계획이 만들어질 거야. 나랑 멋지게 채워보자";
        else if (rate <= 49)
            return "시작이 반이야! 이제 슬슬 몸 풀렸으니까 한 단계씩 차근차근 가보자~";
        else if (rate <= 69)
            return "벌써 절반이나 했잖아! 근데 지금부터가 진짜야. 기세 꺾이지 말고 계속 가자!";
        else if (rate <= 79)
            return "이번 학기 F만 안 나오면 졸업 가능해!";
        else if (rate <= 89)
            return "이제 끝이 보여! 방심 금지! 이때 삐끗하면 다음 학기 또 봐야 돼";
        else if (rate <= 99)
            return "이제 끝이 보여! 졸업까지는 거의 다 왔네. 마무리까지 잘해보자~!";
        else
            return "졸업 요건 달성! 수고했어~";
    }
}
