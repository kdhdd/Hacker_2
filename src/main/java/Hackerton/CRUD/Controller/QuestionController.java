package Hackerton.CRUD.Controller;

import Hackerton.CRUD.domain.Question;
import Hackerton.CRUD.dto.QuestionDto;
import Hackerton.CRUD.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    // 유저에게 매일 3개의 질문 반환
    @GetMapping("/daily")
    public List<Question> getDailyQuestions() {
        return questionService.getDailyQuestions();
    }

    @PostMapping
    public Question createQuestion(@RequestBody QuestionDto questionDto) {
        return questionService.createQuestion(questionDto);
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id);
    }

    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable Long id, @RequestBody QuestionDto questionDto) {
        return questionService.updateQuestion(id, questionDto);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }
}
