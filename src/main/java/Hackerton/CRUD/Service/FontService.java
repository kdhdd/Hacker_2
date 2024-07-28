package Hackerton.CRUD.Service;

import Hackerton.CRUD.domain.Font;
import Hackerton.CRUD.dto.FontDto;
import Hackerton.CRUD.Repository.FontRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FontService {

    private final FontRepository fontRepository;

    public Font createFont(FontDto fontDto) {
        Font font = new Font();
        font.setFontName(fontDto.getFontName());
        font.setFontPointCost(fontDto.getFontPointCost());
        return fontRepository.save(font);
    }

    public List<Font> getAllFonts() {
        return fontRepository.findAll();
    }

    public Font getFontById(Long id) {
        return fontRepository.findById(id).orElse(null);
    }

    public Font updateFont(Long id, FontDto fontDto) {
        Font font = getFontById(id);
        if (font != null) {
            font.setFontName(fontDto.getFontName());
            font.setFontPointCost(fontDto.getFontPointCost());
            return fontRepository.save(font);
        }
        return null;
    }

    public void deleteFont(Long id) {
        fontRepository.deleteById(id);
    }
}
