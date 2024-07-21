package Hackerton.CRUD.Controller;

import Hackerton.CRUD.domain.Like;
import Hackerton.CRUD.dto.LikeDto;
import Hackerton.CRUD.Service.LikeService;
import Hackerton.CRUD.Service.MemberService;
import Hackerton.CRUD.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<LikeDto> registerLike(@RequestBody LikeDto likeDto) {
        Like like = new Like();
        like.setMember(memberService.getMemberById(likeDto.getMemberId()).orElse(null));
        like.setPost(postService.getPostById(likeDto.getPostId()).orElse(null));

        Like registeredLike = likeService.registerLike(like);
        return ResponseEntity.ok(new LikeDto(registeredLike));
    }

    @GetMapping
    public List<Like> getAllLikes() {
        return likeService.getAllLikes();
    }

    @GetMapping("/{id}")
    public Like getLikeById(@PathVariable Long id) {
        return likeService.getLikeById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteLike(@PathVariable Long id) {
        likeService.deleteLike(id);
    }
}
