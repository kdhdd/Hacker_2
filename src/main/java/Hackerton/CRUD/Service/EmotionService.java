//package Hackerton.CRUD.Service;
//
//import Hackerton.CRUD.domain.Emotion;
//import Hackerton.CRUD.dto.EmotionDto;
//import Hackerton.CRUD.Repository.EmotionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class EmotionService {
//
//    @Autowired
//    private EmotionRepository emotionRepository;
//
//    public Emotion createEmotion(EmotionDto emotionDto) {
//        Emotion emotion = new Emotion();
//        emotion.setEmotionName(emotionDto.getEmotionName());
//        emotion.setEmoji(emotionDto.getEmoji());
//        return emotionRepository.save(emotion);
//    }
//
//    public List<Emotion> getAllEmotions() {
//        return emotionRepository.findAll();
//    }
//
//    public Emotion getEmotionById(Long id) {
//        return emotionRepository.findById(id).orElse(null);
//    }
//
//    public Emotion updateEmotion(Long id, EmotionDto emotionDto) {
//        Emotion emotion = getEmotionById(id);
//        if (emotion != null) {
//            emotion.setEmotionName(emotionDto.getEmotionName());
//            emotion.setEmoji(emotionDto.getEmoji());
//            return emotionRepository.save(emotion);
//        }
//        return null;
//    }
//
//    public void deleteEmotion(Long id) {
//        emotionRepository.deleteById(id);
//    }
//}
