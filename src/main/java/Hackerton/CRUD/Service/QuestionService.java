package Hackerton.CRUD.Service;

import Hackerton.CRUD.domain.Question;
import Hackerton.CRUD.domain.QuestionHistory;
import Hackerton.CRUD.dto.QuestionDto;
import Hackerton.CRUD.Repository.QuestionRepository;
import Hackerton.CRUD.Repository.QuestionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    private final QuestionHistoryRepository questionHistoryRepository;

    public Question createQuestion(QuestionDto questionDto) {
        Question question = new Question();
        question.setContent(questionDto.getContent());
        question.setCreatedAt(LocalDateTime.now());
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    public Question updateQuestion(Long id, QuestionDto questionDto) {
        Question question = getQuestionById(id);
        if (question != null) {
            question.setContent(questionDto.getContent());
            return questionRepository.save(question);
        }
        return null;
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public List<Question> getDailyQuestions() {
        List<Question> allQuestions = questionRepository.findAll();
        List<Long> usedQuestionIds = questionHistoryRepository.findByDate(LocalDate.now())
                .stream()
                .map(qh -> qh.getQuestion().getId())
                .collect(Collectors.toList());

        List<Question> availableQuestions = allQuestions.stream()
                .filter(q -> !usedQuestionIds.contains(q.getId()))
                .collect(Collectors.toList());

        Random random = new Random();
        int questionCount = availableQuestions.size();
        int numQuestions = Math.min(3, questionCount);

        List<Question> dailyQuestions = random.ints(0, questionCount)
                .distinct()
                .limit(numQuestions)
                .mapToObj(availableQuestions::get)
                .collect(Collectors.toList());

        for (Question question : dailyQuestions) {
            QuestionHistory questionHistory = new QuestionHistory();
            questionHistory.setQuestion(question);
            questionHistory.setDate(LocalDate.now());
            questionHistoryRepository.save(questionHistory);
        }

        return dailyQuestions;
    }
}
