package Hackerton.CRUD.Controller;

import Hackerton.CRUD.dto.PostDto;
import Hackerton.CRUD.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        PostDto postDto = postService.getPostById(id);
        return postDto != null ? ResponseEntity.ok(postDto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.createPost(postDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    // 같은 주제를 선택한 사람들끼리 묶어서 보여주는 엔드포인트
    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<PostDto>> getPostsByQuestionId(@PathVariable Long questionId) {
        return ResponseEntity.ok(postService.getPostsByQuestionId(questionId));
    }

    // 인기순으로 정렬하여 보여주는 엔드포인트
    @GetMapping("/question/{questionId}/popular")
    public ResponseEntity<List<PostDto>> getPostsByQuestionIdOrderByHeartCountDesc(@PathVariable Long questionId) {
        return ResponseEntity.ok(postService.getPostsByQuestionIdOrderByHeartCountDesc(questionId));
    }

    // 최신순으로 정렬하여 보여주는 엔드포인트
    @GetMapping("/question/{questionId}/recent")
    public ResponseEntity<List<PostDto>> getPostsByQuestionIdOrderByCreatedAtDesc(@PathVariable Long questionId) {
        return ResponseEntity.ok(postService.getPostsByQuestionIdOrderByCreatedAtDesc(questionId));
    }

//    // 특정 감정별로 글을 분류하여 보여주는 엔드포인트
//    @GetMapping("/question/{questionId}/emotion/{emotionId}")
//    public ResponseEntity<List<PostDto>> getPostsByQuestionIdAndEmotionId(@PathVariable Long questionId, @PathVariable Long emotionId) {
//        return ResponseEntity.ok(postService.getPostsByQuestionIdAndEmotionId(questionId, emotionId));
//    }
//
//    // 특정 감정별로 최신순으로 정렬하여 보여주는 엔드포인트
//    @GetMapping("/question/{questionId}/emotion/{emotionId}/recent")
//    public ResponseEntity<List<PostDto>> getPostsByQuestionIdAndEmotionIdOrderByCreatedAtDesc(@PathVariable Long questionId, @PathVariable Long emotionId) {
//        return ResponseEntity.ok(postService.getPostsByQuestionIdAndEmotionIdOrderByCreatedAtDesc(questionId, emotionId));
//    }
//
//    // 특정 감정별로 인기순으로 정렬하여 보여주는 엔드포인트
//    @GetMapping("/question/{questionId}/emotion/{emotionId}/popular")
//    public ResponseEntity<List<PostDto>> getPostsByQuestionIdAndEmotionIdOrderByHeartCountDesc(@PathVariable Long questionId, @PathVariable Long emotionId) {
//        return ResponseEntity.ok(postService.getPostsByQuestionIdAndEmotionIdOrderByHeartCountDesc(questionId, emotionId));
//    }

    // 글 수정 기능
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody PostDto postDto) {
        PostDto updatedPost = postService.updatePost(id, postDto);
        return updatedPost != null ? ResponseEntity.ok(updatedPost) : ResponseEntity.notFound().build();
    }

    // 글 작성 도중 임시 저장 기능
    @PostMapping("/save-temp")
    public ResponseEntity<Void> saveTempPost(@RequestBody PostDto postDto) {
        postService.saveTempPost(postDto);
        return ResponseEntity.noContent().build();
    }

    // 다른 주제의 글을 코인으로 조회하는 기능
    @GetMapping("/{postId}/view")
    public ResponseEntity<PostDto> viewPostWithCoin(@PathVariable Long postId, @RequestParam Long memberId) {
        PostDto postDto = postService.viewPostWithCoin(postId, memberId);
        return postDto != null ? ResponseEntity.ok(postDto) : ResponseEntity.notFound().build();
    }
}

