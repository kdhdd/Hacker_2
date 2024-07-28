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
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    private final EmotionRepository emotionRepository;
    private final PostFontRepository postFontRepository;
    private final PostBackgroundRepository postBackgroundRepository;



    // 사용자 하루에 한 번 글 작성 제한
    public boolean canUserPostToday(Long memberId, Long teamId) {
        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);
        Optional<Post> existingPost = postRepository.findByMemberIdAndTeamIdAndCreatedAtBetween(memberId, teamId, startOfDay, endOfDay);
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
        if (!canUserPostToday(postDto.getMemberId(), postDto.getTeamId())) {
            throw new IllegalStateException("하루에 한번만 글 작성이 가능합니다!");
        }
        Post post = toEntity(postDto);
        post.setCreatedAt(LocalDateTime.now());
        post = postRepository.save(post);
        savePostFonts(post, postDto.getFontIds());
        savePostBackgrounds(post, postDto.getBackgroundIds());
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

            // Team 객체를 조회하여 설정
            Team team = teamRepository.findById(postDto.getTeamId()).orElse(null);
            if (team != null) {
                post.setTeam(team);
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

            postFontRepository.deleteByPost(post);
            postBackgroundRepository.deleteByPost(post);

            savePostFonts(post, postDto.getFontIds());
            savePostBackgrounds(post, postDto.getBackgroundIds());


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

    // 같은 주제를 선택한 사람들끼리 묶어서 보여주는 기능
    public List<PostDto> getPostsByTeamId(Long teamId) {
        return postRepository.findByTeamId(teamId).stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    // 인기순으로 정렬하여 보여주는 기능
    public List<PostDto> getPostsByTeamIdOrderByHeartCountDesc(Long teamId) {
        return postRepository.findByTeamIdOrderByHeartCountDesc(teamId).stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    // 최신순으로 정렬하여 보여주는 기능
    public List<PostDto> getPostsByTeamIdOrderByCreatedAtDesc(Long teamId) {
        return postRepository.findByTeamIdOrderByCreatedAtDesc(teamId).stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    // 특정 감정별로 글을 분류하여 보여주는 기능
    public List<PostDto> getPostsByTeamIdAndEmotionId(Long teamId, Long emotionId) {
        return postRepository.findByTeamIdAndEmotionId(teamId, emotionId).stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    // 특정 감정별로 최신순 정렬하여 보여주는 기능
    public List<PostDto> getPostsByTeamIdAndEmotionIdOrderByCreatedAtDesc(Long teamId, Long emotionId) {
        return postRepository.findByTeamIdAndEmotionIdOrderByCreatedAtDesc(teamId, emotionId).stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    // 특정 감정별로 인기순 정렬하여 보여주는 기능
    public List<PostDto> getPostsByTeamIdAndEmotionIdOrderByHeartCountDesc(Long teamId, Long emotionId) {
        return postRepository.findByTeamIdAndEmotionIdOrderByHeartCountDesc(teamId, emotionId).stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    private PostDto toDto(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setMemberId(post.getMember() != null ? post.getMember().getId() : null);
        dto.setTeamId(post.getTeam() != null ? post.getTeam().getId() : null);
        dto.setEmotionId(post.getEmotion() != null ? post.getEmotion().getId() : null);
        dto.setFontIds(post.getPostFonts() != null ? post.getPostFonts().stream().map(postFont -> postFont.getFont().getId()).collect(Collectors.toList()) : null);
        dto.setBackgroundIds(post.getPostBackgrounds() != null ? post.getPostBackgrounds().stream().map(postBackground -> postBackground.getBackground().getId()).collect(Collectors.toList()) : null);
        dto.setContent(post.getContent());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setTitle(post.getTitle());
        dto.setHeartCount(post.getHeartCount());
        dto.setTempSave(post.getTempSave());
        dto.setHeartIds(post.getHearts() != null ? post.getHearts().stream().map(Heart::getId).collect(Collectors.toList()) : null);
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

        // Team 객체를 조회하여 설정
        Team team = teamRepository.findById(dto.getTeamId()).orElse(null);
        if (team != null) {
            post.setTeam(team);
        } else {
            throw new IllegalStateException("해당 ID의 팀을 찾을 수 없습니다.");
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
        return post;
    }
    private void savePostFonts(Post post, List<Long> fontIds) {
        if (fontIds != null) {
            for (Long fontId : fontIds) {
                Font font = fontRepository.findById(fontId).orElse(null);
                if (font != null) {
                    PostFont postFont = new PostFont();
                    postFont.setPost(post);
                    postFont.setFont(font);
                    postFontRepository.save(postFont);
                }
            }
        }
    }

    private void savePostBackgrounds(Post post, List<Long> backgroundIds) {
        if (backgroundIds != null) {
            for (Long backgroundId : backgroundIds) {
                Background background = backgroundRepository.findById(backgroundId).orElse(null);
                if (background != null) {
                    PostBackground postBackground = new PostBackground();
                    postBackground.setPost(post);
                    postBackground.setBackground(background);
                    postBackgroundRepository.save(postBackground);
                }
            }
        }
    }


}
