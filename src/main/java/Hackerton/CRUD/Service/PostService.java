package Hackerton.CRUD.Service;

import Hackerton.CRUD.Repository.*;
import Hackerton.CRUD.domain.*;
import Hackerton.CRUD.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
//    private final FontRepository fontRepository;
//    private final BackgroundRepository backgroundRepository;
    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
//    private final EmotionRepository emotionRepository;
//    private final PostFontRepository postFontRepository;
//    private final PostBackgroundRepository postBackgroundRepository;
    private final CoinService coinService;



    // 사용자 하루에 한 번 글 작성 제한
    public boolean canUserPostToday(Long memberId, Long questionId) {
        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);
        Optional<Post> existingPost = postRepository.findByMemberIdAndQuestionIdAndCreatedAtBetween(memberId, questionId, startOfDay, endOfDay); 
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

    // 글 작성 기능
    @Transactional
    public PostDto createPost(PostDto postDto) {
        if (!canUserPostToday(postDto.getMemberId(), postDto.getQuestionId())) {
            throw new IllegalStateException("하루에 한번만 글 작성이 가능합니다!");
        }
        Post post = toEntity(postDto);
        post.setCreatedAt(LocalDateTime.now());
        post = postRepository.save(post);
//        savePostFonts(post, postDto.getFontIds());
//        savePostBackgrounds(post, postDto.getBackgroundIds());
        return PostDto.from(post);
    }

    // 글 삭제 기능
    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
    // 글 수정 기능
    @Transactional
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

            // Question 객체를 조회하여 설정
            Question question = questionRepository.findById(postDto.getQuestionId()).orElse(null);
            if (question != null) {
                post.setQuestion(question);
            } else {
                throw new IllegalStateException("해당 ID의 질문을 찾을 수 없습니다.");
            }

//            // Emotion 객체를 조회하여 설정
//            Emotion emotion = emotionRepository.findById(postDto.getEmotionId()).orElse(null);
//            if (emotion != null) {
//                post.setEmotion(emotion);
//            } else {
//                throw new IllegalStateException("해당 ID의 감정을 찾을 수 없습니다.");
//            }

            post.setContent(postDto.getContent());
            post.setCreatedAt(postDto.getCreatedAt());
            post.setTitle(postDto.getTitle());
            post.setTempSave(postDto.getTempSave());

//            postFontRepository.deleteByPost(post);
//            postBackgroundRepository.deleteByPost(post);
//
//            savePostFonts(post, postDto.getFontIds());
//            savePostBackgrounds(post, postDto.getBackgroundIds());

            post = postRepository.save(post);
            return PostDto.from(post);
        } else {
            return null;
        }
    }

    // 글 작성 도중 저장된 내용을 관리
    @Transactional
    public void saveTempPost(PostDto postDto) {
        Post post = toEntity(postDto);
        post.setTempSave(postDto.getTempSave());
        postRepository.save(post);
    }

    // 같은 주제를 선택한 사람들끼리 묶어서 보여주는 기능
    public List<PostDto> getPostsByQuestionId(Long questionId) {
        return postRepository.findByQuestionIdOrderByCreatedAtDesc(questionId).stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    // 인기순으로 정렬하여 보여주는 기능
    public List<PostDto> getPostsByQuestionIdOrderByHeartCountDesc(Long questionId) {
        return postRepository.findByQuestionIdOrderByHeartCountDesc(questionId).stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    // 최신순으로 정렬하여 보여주는 기능
    public List<PostDto> getPostsByQuestionIdOrderByCreatedAtDesc(Long questionId) {
        return postRepository.findByQuestionIdOrderByCreatedAtDesc(questionId).stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }
//
//    // 특정 감정별로 글을 분류하여 보여주는 기능
//    public List<PostDto> getPostsByQuestionIdAndEmotionId(Long questionId, Long emotionId) {
//        return postRepository.findByQuestionIdAndEmotionId(questionId, emotionId).stream()
//                .map(PostDto::from)
//                .collect(Collectors.toList());
//    }
//
//    // 특정 감정별로 최신순 정렬하여 보여주는 기능
//    public List<PostDto> getPostsByQuestionIdAndEmotionIdOrderByCreatedAtDesc(Long questionId, Long emotionId) {
//        return postRepository.findByQuestionIdAndEmotionIdOrderByCreatedAtDesc(questionId, emotionId).stream()
//                .map(PostDto::from)
//                .collect(Collectors.toList());
//    }
//
//    // 특정 감정별로 인기순 정렬하여 보여주는 기능
//    public List<PostDto> getPostsByQuestionIdAndEmotionIdOrderByHeartCountDesc(Long questionId, Long emotionId) {
//        return postRepository.findByQuestionIdAndEmotionIdOrderByHeartCountDesc(questionId, emotionId).stream()
//                .map(PostDto::from)
//                .collect(Collectors.toList());
//    }
    // 사용자가 다른 주제의 글을 보려고 할 때 코인을 차감하는 기능
    // 코인 사용하여 다른 주제의 글 조회 기능
    @Transactional
    public PostDto viewPostWithCoin(Long postId, Long memberId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        Long questionId = post.getQuestion().getId();
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member not found"));

        // 사용자가 선택한 질문 확인
        MemberQuestion memberQuestion = member.getMemberQuestions().stream()
                .filter(mq -> mq.getQuestion().getId().equals(questionId))
                .findFirst()
                .orElse(null);

        // 사용자가 선택한 질문과 다른 질문의 글을 보려고 하면 코인 차감
        if (memberQuestion == null) {
            coinService.subtractCoins(memberId, 5, "다른 주제의 글 조회");
        }

        return PostDto.from(post);
    }

    // Entity를 DTO로 변환
    private PostDto toDto(Post post) {
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


    // DTO를 Entity로 변환
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

        // Question 객체를 조회하여 설정
        Question question = questionRepository.findById(dto.getQuestionId()).orElse(null);
        if (question != null) {
            post.setQuestion(question);
        } else {
            throw new IllegalStateException("해당 ID의 질문을 찾을 수 없습니다.");
        }

//        // Emotion 객체를 조회하여 설정
//        Emotion emotion = emotionRepository.findById(dto.getEmotionId()).orElse(null);
//        if (emotion != null) {
//            post.setEmotion(emotion);
//        } else {
//            throw new IllegalStateException("해당 ID의 감정을 찾을 수 없습니다.");
//        }

        post.setContent(dto.getContent());
        post.setCreatedAt(dto.getCreatedAt());
        post.setTitle(dto.getTitle());
        post.setTempSave(dto.getTempSave());
        return post;
    }

//    private void savePostFonts(Post post, List<Long> fontIds) {
//        if (fontIds != null) {
//            for (Long fontId : fontIds) {
//                Font font = fontRepository.findById(fontId).orElse(null);
//                if (font != null) {
//                    PostFont postFont = new PostFont();
//                    postFont.setPost(post);
//                    postFont.setFont(font);
//                    postFontRepository.save(postFont);
//                }
//            }
//        }
//    }

//    private void savePostBackgrounds(Post post, List<Long> backgroundIds) {
//        if (backgroundIds != null) {
//            for (Long backgroundId : backgroundIds) {
//                Background background = backgroundRepository.findById(backgroundId).orElse(null);
//                if (background != null) {
//                    PostBackground postBackground = new PostBackground();
//                    postBackground.setPost(post);
//                    postBackground.setBackground(background);
//                    postBackgroundRepository.save(postBackground);
//                }
//            }
//        }
//    }
    // 매일 22시에 1위, 2위, 3위 게시물 선정하여 코인 지급
    //heart 갯수가 가장 많은 게시물
    @Scheduled(cron = "0 0 22 * * ?") // 매일 저녁 22시에 실행
    @Transactional
    public void awardTopPosts() {
        List<Post> topPosts = postRepository.findTop3ByOrderByHeartCountDesc();

        if (!topPosts.isEmpty()) {
            if (topPosts.size() > 0) {
                coinService.addCoins(topPosts.get(0).getMember().getId(), 50, "1위 게시물 보상");
            }
            if (topPosts.size() > 1) {
                coinService.addCoins(topPosts.get(1).getMember().getId(), 30, "2위 게시물 보상");
            }
            if (topPosts.size() > 2) {
                coinService.addCoins(topPosts.get(2).getMember().getId(), 20, "3위 게시물 보상");
            }
        }
    }


}
