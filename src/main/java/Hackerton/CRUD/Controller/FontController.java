package Hackerton.CRUD.Controller;
import Hackerton.CRUD.domain.Font;
import Hackerton.CRUD.dto.FontDto;
import Hackerton.CRUD.Service.FontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fonts")
public class FontController {

    @Autowired
    private FontService fontService;

    @PostMapping
    public Font createFont(@RequestBody FontDto fontDto) {
        return fontService.createFont(fontDto);
    }

    @GetMapping
    public List<Font> getAllFonts() {
        return fontService.getAllFonts();
    }

    @GetMapping("/{id}")
    public Font getFontById(@PathVariable Long id) {
        return fontService.getFontById(id);
    }

    @PutMapping("/{id}")
    public Font updateFont(@PathVariable Long id, @RequestBody FontDto fontDto) {
        return fontService.updateFont(id, fontDto);
    }

    @DeleteMapping("/{id}")
    public void deleteFont(@PathVariable Long id) {
        fontService.deleteFont(id);
    }
}
