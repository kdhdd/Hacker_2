package Hackerton.CRUD.Config;

import Hackerton.CRUD.domain.Question;
import Hackerton.CRUD.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
                ClassPathResource resource = new ClassPathResource("questions.txt");
                List<String> questions;
                try (InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
                    questions = FileCopyUtils.copyToString(reader).lines().collect(Collectors.toList());
                }
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

