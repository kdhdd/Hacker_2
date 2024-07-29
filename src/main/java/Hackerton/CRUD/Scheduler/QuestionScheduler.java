package Hackerton.CRUD.Scheduler;

import Hackerton.CRUD.Service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionScheduler {

    private final QuestionService questionService;

    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
    public void scheduleDailyQuestions() {
        questionService.selectRandomQuestions();
    }
}