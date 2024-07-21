package Hackerton.CRUD.dto;

import Hackerton.CRUD.domain.Like;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeDto {
    private Long id;
    private Long memberId;
    private Long postId;

    public LikeDto() {
        // 기본 생성자
    }

    public LikeDto(Like like) {
        this.id = like.getId();
        this.memberId = like.getMember().getId();
        this.postId = like.getPost().getId();
    }

    public static LikeDto from(Like like) {
        LikeDto likeDto = new LikeDto();
        likeDto.setId(like.getId());
        likeDto.setMemberId(like.getMember().getId());
        likeDto.setPostId(like.getPost().getId());
        return likeDto;
    }
}
