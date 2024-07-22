package Hackerton.CRUD.Controller;

import Hackerton.CRUD.Service.BadgeService;
import Hackerton.CRUD.domain.Badge;
import Hackerton.CRUD.dto.BadgeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/badges")
public class BadgeController {

    private final BadgeService badgeService;

    @Autowired
    public BadgeController(BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    @PostMapping
    public ResponseEntity<BadgeDto> registerBadge(@RequestBody BadgeDto badgeDto) {
        Badge badge = new Badge();
        badge.setBadge_name(badgeDto.getBadge_name());
        badge.setDescription(badgeDto.getDescription());
        Badge registeredBadge = badgeService.registerBadge(badge);
        return ResponseEntity.ok(BadgeDto.from(registeredBadge));
    }

    @GetMapping
    public ResponseEntity<List<BadgeDto>> getAllBadges() {
        List<Badge> badges = badgeService.getAllBadges();
        List<BadgeDto> badgeDtos = new ArrayList<>();
        for (Badge badge : badges) {
            BadgeDto dto = BadgeDto.from(badge);
            badgeDtos.add(dto);
        }
        return ResponseEntity.ok(badgeDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BadgeDto> getBadgeById(@PathVariable(name="id") Long id) {
        Optional<Badge> badgeOptional = badgeService.getBadgeById(id);
        if (badgeOptional.isPresent()) {
            BadgeDto dto = BadgeDto.from(badgeOptional.get());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBadge(@PathVariable Long id) {
        badgeService.deleteBadge(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BadgeDto> UpdateBadge(@PathVariable Long id, @RequestBody BadgeDto badgeDto) {
        try {
            Badge updatedBadge = badgeService.updateBadge(id, badgeDto);
            return ResponseEntity.ok(BadgeDto.from(updatedBadge));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
