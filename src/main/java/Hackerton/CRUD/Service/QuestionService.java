package Hackerton.CRUD.Service;

import Hackerton.CRUD.Repository.QuestionHistoryRepository;
import Hackerton.CRUD.Repository.QuestionRepository;
import Hackerton.CRUD.domain.Question;
import Hackerton.CRUD.domain.QuestionHistory;
import Hackerton.CRUD.dto.QuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionHistoryRepository questionHistoryRepository;
    private static final String QUESTIONS_FILE_PATH = "src/main/resources/questions.txt";

    // 모든 질문 조회
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    // 오늘의 질문 가져오기
    public List<Question> getDailyQuestions() {
        List<QuestionHistory> todayQuestions = questionHistoryRepository.findByDate(LocalDate.now());
        return todayQuestions.stream()
                .map(QuestionHistory::getQuestion)
                .collect(Collectors.toList());
    }

    // 특정 날짜의 질문 가져오기
    public List<Question> getQuestionsByDate(LocalDate date) {
        List<QuestionHistory> questions = questionHistoryRepository.findByDate(date);
        return questions.stream()
                .map(QuestionHistory::getQuestion)
                .collect(Collectors.toList());
    }

    // 파일에서 질문 목록을 읽어오는 메서드
    private List<String> readQuestionsFromFile() {
        try {
            return Files.readAllLines(Paths.get(QUESTIONS_FILE_PATH));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read questions from file", e);
        }
    }

    // 매일 3개의 질문을 무작위로 선택하여 QuestionHistory에 저장
    @Transactional
    public void selectRandomQuestions() {
        List<String> allQuestions = readQuestionsFromFile();

        List<String> usedQuestions = questionHistoryRepository.findAll()
                .stream()
                .map(questionHistory -> questionHistory.getQuestion().getContent())
                .collect(Collectors.toList());

        List<String> availableQuestions = allQuestions.stream()
                .filter(question -> !usedQuestions.contains(question))
                .collect(Collectors.toList());

        Random random = new Random();
        int questionCount = availableQuestions.size();
        int numQuestions = Math.min(3, questionCount);

        List<String> dailyQuestions = random.ints(0, questionCount)
                .distinct()
                .limit(numQuestions)
                .mapToObj(availableQuestions::get)
                .collect(Collectors.toList());

        for (int i = 0; i < dailyQuestions.size(); i++) {
            String questionContent = dailyQuestions.get(i);
            Question question = new Question();
            question.setContent(questionContent);
            question.setCreatedAt(LocalDateTime.now());

            // 고정된 ID 설정
            long fixedId = i + 1;
            if (questionRepository.existsById(fixedId)) {
                Question existingQuestion = questionRepository.findById(fixedId).orElse(null);
                if (existingQuestion != null) {
                    questionRepository.delete(existingQuestion);
                }
            }
            question.setId(fixedId);
            questionRepository.save(question);

            QuestionHistory questionHistory = new QuestionHistory();
            questionHistory.setQuestion(question);
            questionHistory.setDate(LocalDate.now());
            questionHistoryRepository.save(questionHistory);
        }
    }
}
