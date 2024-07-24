package Hackerton.CRUD.Controller;

import Hackerton.CRUD.domain.Background;
import Hackerton.CRUD.dto.BackgroundDto;
import Hackerton.CRUD.Service.BackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/backgrounds")
public class BackgroundController {

    @Autowired
    private BackgroundService backgroundService;

    @PostMapping
    public Background createBackground(@RequestBody BackgroundDto backgroundDto) {
        return backgroundService.createBackground(backgroundDto);
    }

    @GetMapping
    public List<Background> getAllBackgrounds() {
        return backgroundService.getAllBackgrounds();
    }

    @GetMapping("/{id}")
    public Background getBackgroundById(@PathVariable Long id) {
        return backgroundService.getBackgroundById(id);
    }

    @PutMapping("/{id}")
    public Background updateBackground(@PathVariable Long id, @RequestBody BackgroundDto backgroundDto) {
        return backgroundService.updateBackground(id, backgroundDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBackground(@PathVariable Long id) {
        backgroundService.deleteBackground(id);
    }
}