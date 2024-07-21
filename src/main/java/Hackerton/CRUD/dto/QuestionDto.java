package Hackerton.CRUD.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QuestionDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime selectedAt;
}
