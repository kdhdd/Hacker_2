package Hackerton.CRUD.Service;

import Hackerton.CRUD.Repository.*;
import Hackerton.CRUD.domain.*;
import Hackerton.CRUD.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final GroupRepository groupRepository;
    private final MemberRepository memberRepository;
    private final EmotionRepository emotionRepository;



    // 사용자 하루에 한 번 글 작성 제한
    public boolean canUserPostToday(Long memberId, Long groupId) {
        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);
        Optional<Post> existingPost = postRepository.findByMemberIdAndGroupIdAndCreatedAtBetween(memberId, groupId, startOfDay, endOfDay);
        return existingPost.isEmpty();
    }

    //모든 글 조회 기능
    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream().map(PostDto::from).collect(Collectors.toList());
    }

    //글 조회 기능
    public PostDto getPostById(Long id) {
        return postRepository.findById(id).map(PostDto::from).orElse(null);
    }

    //글 작성 기능
    public PostDto createPost(PostDto postDto) {
        if (!canUserPostToday(postDto.getMemberId(), postDto.getGroupId())) {
            throw new IllegalStateException("하루에 한번만 글 작성이 가능합니다!");
        }
        Post post = toEntity(postDto);
        post.setCreatedAt(LocalDateTime.now());
        post = postRepository.save(post);
        return PostDto.from(post);
    }

    //글 삭제 기능
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    //글 수정 기능
    public PostDto updatePost(Long id, PostDto postDto) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent()) {
            Post post = existingPost.get();

            // Member 객체를 조회하여 설정
            Member member = memberRepository.findById(postDto.getMemberId()).orElse(null);
            if (member != null) {
                post.setMember(member);
            } else {
                throw new IllegalStateException("해당 ID의 회원을 찾을 수 없습니다.");
            }

            // Group 객체를 조회하여 설정
            Group group = groupRepository.findById(postDto.getGroupId()).orElse(null);
            if (group != null) {
                post.setGroup(group);
            } else {
                throw new IllegalStateException("해당 ID의 그룹을 찾을 수 없습니다.");
            }
            // Emotion 객체를 조회하여 설정
            Emotion emotion = emotionRepository.findById(postDto.getEmotionId()).orElse(null);
            if (emotion != null) {
                post.setEmotion(emotion);
            } else {
                throw new IllegalStateException("해당 ID의 감정을 찾을 수 없습니다.");
            }

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


    public Post toEntity(PostDto dto) {
        Post post = new Post();
        post.setId(dto.getId());

        // Member 객체를 조회하여 설정
        Member member = memberRepository.findById(dto.getMemberId()).orElse(null);
        if (member != null) {
            post.setMember(member);
        } else {
            throw new IllegalStateException("해당 ID의 회원을 찾을 수 없습니다.");
        }

        // Group 객체를 조회하여 설정
        Group group = groupRepository.findById(dto.getGroupId()).orElse(null);
        if (group != null) {
            post.setGroup(group);
        } else {
            throw new IllegalStateException("해당 ID의 그룹을 찾을 수 없습니다.");
        }

        // Emotion 객체를 조회하여 설정
        Emotion emotion = emotionRepository.findById(dto.getEmotionId()).orElse(null);
        if (emotion != null) {
            post.setEmotion(emotion);
        } else {
            throw new IllegalStateException("해당 ID의 감정을 찾을 수 없습니다.");
        }

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
