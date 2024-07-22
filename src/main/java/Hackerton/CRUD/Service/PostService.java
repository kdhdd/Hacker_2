package Hackerton.CRUD.Service;
import Hackerton.CRUD.domain.Group;
import Hackerton.CRUD.domain.Like;
import Hackerton.CRUD.domain.Post;
import Hackerton.CRUD.domain.Font;
import Hackerton.CRUD.domain.Background;
import Hackerton.CRUD.dto.PostDto;
import Hackerton.CRUD.Repository.PostRepository;
import Hackerton.CRUD.Repository.FontRepository;
import Hackerton.CRUD.Repository.BackgroundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final FontRepository fontRepository;
    private final BackgroundRepository backgroundRepository;



    // 사용자 하루에 한 번 글 작성 제한
    public boolean canUserPostToday(Long memberId, Long groupId) {
        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);
        Optional<Post> existingPost = postRepository.findByMemberIdAndGroupIdAndCreatedAtBetween(memberId, groupId, startOfDay, endOfDay);
        return existingPost.isEmpty();
    }

    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream().map(PostDto::from).collect(Collectors.toList());
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public PostDto createPost(PostDto postDto) {
        if (!canUserPostToday(postDto.getMemberId(), postDto.getGroupId())) {
            throw new IllegalStateException("하루에 한번만 글 작성이 가능합니다!");
        }
        Post post = toEntity(postDto);
        post.setCreatedAt(LocalDateTime.now());
        post = postRepository.save(post);
        return PostDto.from(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public PostDto updatePost(Long id, PostDto postDto) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent()) {
            Post post = existingPost.get();
            post.setMember(postDto.getMemberId());
            post.setGroup(postDto.getGroupId());
            post.setEmotion(postDto.getEmotionId());
            post.setContent(postDto.getContent());
            post.setCreatedAt(postDto.getCreatedAt());
            post.setTitle(postDto.getTitle());
            post.setTempSave(postDto.getTempSave());

            List<Font> fonts = fontRepository.findAllById(postDto.getFontIds());
            post.setFonts(fonts);
            List<Background> backgrounds = backgroundRepository.findAllById(postDto.getBackgroundIds());
            post.setBackgrounds(backgrounds);

            post = postRepository.save(post);
            return PostDto.from(post);
        } else {
            return null;
        }
    }

    //글 작성 도중 저장된 내용을 관리
    public void saveTempPost(PostDto postDto) {
        Post post = toEntity(postDto);
        post.setTempSave(postDto.getTempSave());
        postRepository.save(post);
    }

    private PostDto toDto(Post post) {
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


    private Post toEntity(PostDto dto) {
        Post post = new Post();
        post.setId(dto.getId());
        post.setMember(dto.getMemberId());
        post.setGroup(dto.getGroupId());
        post.setEmotion(dto.getEmotionId());
        post.setContent(dto.getContent());
        post.setCreatedAt(dto.getCreatedAt());
        post.setTitle(dto.getTitle());
        post.setTempSave(dto.getTempSave());

        List<Font> fonts = fontRepository.findAllById(dto.getFontIds());
        post.setFonts(fonts);
        List<Background> backgrounds = backgroundRepository.findAllById(dto.getBackgroundIds());
        post.setBackgrounds(backgrounds);

        return post;
    }
}
