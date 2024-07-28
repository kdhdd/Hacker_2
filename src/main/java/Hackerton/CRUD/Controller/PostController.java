package Hackerton.CRUD.Controller;

import Hackerton.CRUD.dto.PostDto;
import Hackerton.CRUD.Service.PostService;
import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
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
    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<PostDto>> getPostsByTeamId(@PathVariable Long teamId) {
        return ResponseEntity.ok(postService.getPostsByTeamId(teamId));
    }

    // 인기순으로 정렬하여 보여주는 엔드포인트
    @GetMapping("/team/{teamId}/popular")
    public ResponseEntity<List<PostDto>> getPostsByTeamIdOrderByHeartCountDesc(@PathVariable Long teamId) {
        return ResponseEntity.ok(postService.getPostsByTeamIdOrderByHeartCountDesc(teamId));
    }

    // 최신순으로 정렬하여 보여주는 엔드포인트
    @GetMapping("/team/{teamId}/recent")
    public ResponseEntity<List<PostDto>> getPostsByTeamIdOrderByCreatedAtDesc(@PathVariable Long teamId) {
        return ResponseEntity.ok(postService.getPostsByTeamIdOrderByCreatedAtDesc(teamId));
    }

    // 특정 감정별로 글을 분류하여 보여주는 엔드포인트
    @GetMapping("/team/{teamId}/emotion/{emotionId}")
    public ResponseEntity<List<PostDto>> getPostsByTeamIdAndEmotionId(@PathVariable Long teamId, @PathVariable Long emotionId) {
        return ResponseEntity.ok(postService.getPostsByTeamIdAndEmotionId(teamId, emotionId));
    }

    // 특정 감정별로 최신순으로 정렬하여 보여주는 엔드포인트
    @GetMapping("/team/{teamId}/emotion/{emotionId}/recent")
    public ResponseEntity<List<PostDto>> getPostsByTeamIdAndEmotionIdOrderByCreatedAtDesc(@PathVariable Long teamId, @PathVariable Long emotionId) {
        return ResponseEntity.ok(postService.getPostsByTeamIdAndEmotionIdOrderByCreatedAtDesc(teamId, emotionId));
    }

    // 특정 감정별로 인기순으로 정렬하여 보여주는 엔드포인트
    @GetMapping("/team/{teamId}/emotion/{emotionId}/popular")
    public ResponseEntity<List<PostDto>> getPostsByTeamIdAndEmotionIdOrderByHeartCountDesc(@PathVariable Long teamId, @PathVariable Long emotionId) {
        return ResponseEntity.ok(postService.getPostsByTeamIdAndEmotionIdOrderByHeartCountDesc(teamId, emotionId));
    }

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
}

