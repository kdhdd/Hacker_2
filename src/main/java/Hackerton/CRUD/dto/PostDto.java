package Hackerton.CRUD.dto;


import Hackerton.CRUD.domain.Font;
import Hackerton.CRUD.domain.Background;
import Hackerton.CRUD.domain.Like;
import Hackerton.CRUD.domain.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PostDto {
    private Long id;
    private Long memberId;
    private Long groupId;
    private Long emotionId;
    private List<Long> fontIds;
    private List<Long> backgroundIds;
    private String content;
    private LocalDateTime createdAt;
    private String title;
    private Integer likeCount;
    private String tempSave;
    private List<Long> likeIds;

    public static PostDto from(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setMemberId(post.getMember() != null ? post.getMember().getId() : null);
        dto.setGroupId(post.getGroup() != null ? post.getGroup().getId() : null);
        dto.setEmotionId(post.getEmotion() != null ? post.getEmotion().getId() : null);
        dto.setFontIds(post.getFonts() != null ? post.getFonts().stream().map(Font::getId).collect(Collectors.toList()) : null);
        dto.setBackgroundIds(post.getBackgrounds() != null ? post.getBackgrounds().stream().map(Background::getId).collect(Collectors.toList()) : null);
        dto.setContent(post.getContent());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setTitle(post.getTitle());
        dto.setLikeCount(post.getLikeCount());
        dto.setTempSave(post.getTempSave());
        dto.setLikeIds(post.getLikes() != null ? post.getLikes().stream().map(Like::getId).collect(Collectors.toList()) : null);
        return dto;
    }
}

