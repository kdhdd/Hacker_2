package Hackerton.CRUD.dto;

import lombok.Getter;
import lombok.Setter;
import Hackerton.CRUD.domain.Comment;
import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private String content;
    private LocalDateTime registerDate;
    private Long memberId;

    public static CommentDto from(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        commentDto.setRegisterDate(comment.getRegisterDate());
        if (comment.getMember() != null) {
            commentDto.setMemberId(comment.getMember().getId());
        }
        return commentDto;
    }
}
