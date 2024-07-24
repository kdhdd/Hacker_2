package Hackerton.CRUD.Service;

import Hackerton.CRUD.domain.Heart;
import Hackerton.CRUD.Repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;

    public Heart registerHeart(Heart heart) {
        return heartRepository.save(heart);
    }

    public List<Heart> getAllHearts() {
        return heartRepository.findAll();
    }

    public Heart getHeartById(Long id) {
        return heartRepository.findById(id).orElse(null);
    }

    public void deleteHeart(Long id) {
        heartRepository.deleteById(id);
    }
}