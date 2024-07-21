package Hackerton.CRUD.Service;

import Hackerton.CRUD.domain.Question;
import Hackerton.CRUD.domain.QuestionHistory;
import Hackerton.CRUD.dto.QuestionHistoryDto;
import Hackerton.CRUD.Repository.QuestionHistoryRepository;
import Hackerton.CRUD.Repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionHistoryService {

    @Autowired
    private final QuestionHistoryRepository questionHistoryRepository;

    @Autowired
    private final QuestionRepository questionRepository;

    public QuestionHistory createQuestionHistory(QuestionHistoryDto questionHistoryDto) {
        QuestionHistory questionHistory = new QuestionHistory();
        Question question = questionRepository.findById(questionHistoryDto.getQuestionId()).orElse(null);
        if (question == null) {
            throw new RuntimeException("해당 ID로 질문을 찾을 수 없습니다:" + questionHistoryDto.getQuestionId());
        }
        questionHistory.setQuestion(question);
        questionHistory.setDate(LocalDate.now());
        return questionHistoryRepository.save(questionHistory);
    }

    public List<QuestionHistory> getAllQuestionHistories() {
        return questionHistoryRepository.findAll();
    }

    public QuestionHistory getQuestionHistoryById(Long id) {
        return questionHistoryRepository.findById(id).orElse(null);
    }
}
