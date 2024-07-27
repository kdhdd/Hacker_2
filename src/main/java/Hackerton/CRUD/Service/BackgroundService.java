package Hackerton.CRUD.Service;

import Hackerton.CRUD.domain.Background;
import Hackerton.CRUD.dto.BackgroundDto;
import Hackerton.CRUD.Repository.BackgroundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackgroundService {

    @Autowired
    private BackgroundRepository backgroundRepository;

    public Background createBackground(BackgroundDto backgroundDto) {
        Background background = new Background();
        background.setBackgroundColor(backgroundDto.getBackgroundColor());
        background.setBackgroundPointCost(backgroundDto.getBackgroundPointCost());
        return backgroundRepository.save(background);
    }

    public List<Background> getAllBackgrounds() {
        return backgroundRepository.findAll();
    }

    public Background getBackgroundById(Long id) {
        return backgroundRepository.findById(id).orElse(null);
    }

    public Background updateBackground(Long id, BackgroundDto backgroundDto) {
        Background background = getBackgroundById(id);
        if (background != null) {
            background.setBackgroundColor(backgroundDto.getBackgroundColor());
            background.setBackgroundPointCost(backgroundDto.getBackgroundPointCost());
            return backgroundRepository.save(background);
        }
        return null;
    }

    public void deleteBackground(Long id) {
        backgroundRepository.deleteById(id);
    }
}