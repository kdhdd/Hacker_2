package Hackerton.CRUD.Controller;

import Hackerton.CRUD.domain.Heart;
import Hackerton.CRUD.domain.Post;
import Hackerton.CRUD.dto.HeartDto;
import Hackerton.CRUD.Service.HeartService;
import Hackerton.CRUD.Service.MemberService;
import Hackerton.CRUD.Service.PostService;
import Hackerton.CRUD.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hearts")
public class HeartController {

    @Autowired
    private HeartService heartService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<HeartDto> registerHeart(@RequestBody HeartDto heartDto) {
        Heart heart = new Heart();
        heart.setMember(memberService.getMemberById(heartDto.getMemberId()).orElse(null));

        // PostDto를 Post 객체로 변환
        PostDto postDto = postService.getPostById(heartDto.getPostId());
        if (postDto == null) {
            return ResponseEntity.badRequest().build();
        }
        Post post = postService.toEntity(postDto);

        heart.setPost(post);

        Heart registeredHeart = heartService.registerHeart(heart);
        return ResponseEntity.ok(new HeartDto(registeredHeart));
    }

    @GetMapping
    public List<Heart> getAllHearts() {
        return heartService.getAllHearts();
    }

    @GetMapping("/{id}")
    public Heart getHeartById(@PathVariable Long id) {
        return heartService.getHeartById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteHeart(@PathVariable Long id) {
        heartService.deleteHeart(id);
    }
}

