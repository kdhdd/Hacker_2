package Hackerton.CRUD.Controller;


import Hackerton.CRUD.domain.Question;
import Hackerton.CRUD.dto.QuestionDto;
import Hackerton.CRUD.Service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    // 유저에게 매일 3개의 질문 반환
    @GetMapping("/daily")
    public ResponseEntity<List<Question>> getDailyQuestions() {
        List<Question> dailyQuestions = questionService.getDailyQuestions();
        return ResponseEntity.ok(dailyQuestions);
    }

    // 특정 날짜의 질문 조회
    @GetMapping("/by-date")
    public ResponseEntity<List<Question>> getQuestionsByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Question> questions = questionService.getQuestionsByDate(date);
        return ResponseEntity.ok(questions);
    }

    // 모든 질문 조회
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }
}
