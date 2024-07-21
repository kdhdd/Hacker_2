package Hackerton.CRUD.Service;

import Hackerton.CRUD.domain.Like;
import Hackerton.CRUD.Repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public Like registerLike(Like like) {
        return likeRepository.save(like);
    }

    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    public Like getLikeById(Long id) {
        return likeRepository.findById(id).orElse(null);
    }

    public void deleteLike(Long id) {
        likeRepository.deleteById(id);
    }
}