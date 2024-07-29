package Hackerton.CRUD.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="question_id")
    private Long id;

    private String content;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "question")
    private List<MemberQuestion> memberQuestions;

    @OneToMany(mappedBy = "question")
    private List<Post> posts;
}
