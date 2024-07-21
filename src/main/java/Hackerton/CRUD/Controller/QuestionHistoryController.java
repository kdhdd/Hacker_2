package Hackerton.CRUD.Controller;

import Hackerton.CRUD.domain.QuestionHistory;
import Hackerton.CRUD.dto.QuestionHistoryDto;
import Hackerton.CRUD.Service.QuestionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question-histories")
public class QuestionHistoryController {

    @Autowired
    private QuestionHistoryService questionHistoryService;

    @PostMapping
    public QuestionHistory createQuestionHistory(@RequestBody QuestionHistoryDto questionHistoryDto) {
        return questionHistoryService.createQuestionHistory(questionHistoryDto);
    }

    @GetMapping
    public List<QuestionHistory> getAllQuestionHistories() {
        return questionHistoryService.getAllQuestionHistories();
    }

    @GetMapping("/{id}")
    public QuestionHistory getQuestionHistoryById(@PathVariable Long id) {
        return questionHistoryService.getQuestionHistoryById(id);
    }
}
