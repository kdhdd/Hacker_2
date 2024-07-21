package Hackerton.CRUD.Controller;

import Hackerton.CRUD.domain.Emotion;
import Hackerton.CRUD.dto.EmotionDto;
import Hackerton.CRUD.Service.EmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emotions")
public class EmotionController {

    @Autowired
    private EmotionService emotionService;

    @PostMapping
    public Emotion createEmotion(@RequestBody EmotionDto emotionDto) {
        return emotionService.createEmotion(emotionDto);
    }

    @GetMapping
    public List<Emotion> getAllEmotions() {
        return emotionService.getAllEmotions();
    }

    @GetMapping("/{id}")
    public Emotion getEmotionById(@PathVariable Long id) {
        return emotionService.getEmotionById(id);
    }

    @PutMapping("/{id}")
    public Emotion updateEmotion(@PathVariable Long id, @RequestBody EmotionDto emotionDto) {
        return emotionService.updateEmotion(id, emotionDto);
    }

    @DeleteMapping("/{id}")
    public void deleteEmotion(@PathVariable Long id) {
        emotionService.deleteEmotion(id);
    }
}
