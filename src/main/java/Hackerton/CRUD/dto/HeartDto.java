package Hackerton.CRUD.dto;

import Hackerton.CRUD.domain.Heart;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeartDto {
    private Long id;
    private Long memberId;
    private Long postId;

    public HeartDto() {
        // 기본 생성자
    }

    public HeartDto(Heart heart) {
        this.id = heart.getId();
        this.memberId = heart.getMember().getId();
        this.postId = heart.getPost().getId();
    }

    public static HeartDto from(Heart heart) {
        HeartDto heartDto = new HeartDto();
        heartDto.setId(heart.getId());
        heartDto.setMemberId(heart.getMember().getId());
        heartDto.setPostId(heart.getPost().getId());
        return heartDto;
    }
}
