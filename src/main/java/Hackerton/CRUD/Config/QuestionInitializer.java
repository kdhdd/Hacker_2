package Hackerton.CRUD.Config;

import Hackerton.CRUD.domain.Question;
import Hackerton.CRUD.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class QuestionInitializer {

    @Autowired
    private QuestionRepository questionRepository;

    @Bean
    public CommandLineRunner loadInitialQuestions() {
        return args -> {
            if (questionRepository.count() == 0) {
                List<String> questions = Files.readAllLines(Paths.get("src/main/resources/questions.txt"));
                List<Question> questionEntities = questions.stream()
                        .map(content -> {
                            Question question = new Question();
                            question.setContent(content);
                            question.setCreatedAt(LocalDateTime.now());
                            return question;
                        })
                        .collect(Collectors.toList());
                questionRepository.saveAll(questionEntities);
            }
        };
    }
}

