package Hackerton.CRUD.dto;


//import Hackerton.CRUD.domain.Font;
//import Hackerton.CRUD.domain.Background;
import Hackerton.CRUD.domain.Heart;
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
    private Long questionId;
    private Long emotionId;
    private List<Long> fontIds;
    private List<Long> backgroundIds;
    private String content;
    private LocalDateTime createdAt;
    private String title;
    private Integer heartCount;
    private String tempSave;
    private List<Long> heartIds;

    public static PostDto from(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setMemberId(post.getMember() != null ? post.getMember().getId() : null);
        dto.setQuestionId(post.getQuestion() != null ? post.getQuestion().getId() : null);
//        dto.setEmotionId(post.getEmotion() != null ? post.getEmotion().getId() : null);
//        dto.setFontIds(post.getPostFonts() != null ? post.getPostFonts().stream().map(postFont -> postFont.getFont().getId()).collect(Collectors.toList()) : null);
//        dto.setBackgroundIds(post.getPostBackgrounds() != null ? post.getPostBackgrounds().stream().map(postBackground -> postBackground.getBackground().getId()).collect(Collectors.toList()) : null);
        dto.setContent(post.getContent());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setTitle(post.getTitle());
        dto.setHeartCount(post.getHeartCount());
        dto.setTempSave(post.getTempSave());
        dto.setHeartIds(post.getHearts() != null ? post.getHearts().stream().map(Heart::getId).collect(Collectors.toList()) : null);
        return dto;
    }
}

