package Hackerton.CRUD.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberQuestionDto {
    private Long memberId;
    private Long questionId;
    private LocalDateTime selectedAt;
}