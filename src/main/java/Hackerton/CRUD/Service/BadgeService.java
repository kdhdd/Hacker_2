package Hackerton.CRUD.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Hackerton.CRUD.Repository.BadgeRepository;
import Hackerton.CRUD.domain.Badge;
import Hackerton.CRUD.dto.BadgeDto;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BadgeService {
    private final BadgeRepository badgeRepository;

    @Autowired
    public BadgeService(BadgeRepository badgeRepository){
        this.badgeRepository = badgeRepository;
    }
    @Transactional
    public Badge registerBadge(Badge badge) {
        return badgeRepository.save(badge);
    }
    @Transactional
    public void deleteBadge(Long id) {
        badgeRepository.deleteById(id);
    }
    @Transactional
    public Badge updateBadge(Long id, BadgeDto badgeDto) {
        Optional<Badge> badgeOptional = badgeRepository.findById(id);
        if (badgeOptional.isPresent()) {
            Badge badge = badgeOptional.get();
            if (badgeDto.getBadge_name() != null) {
                badge.setBadge_name(badgeDto.getBadge_name());
            }
            if (badgeDto.getDescription() != null) {
                badge.setDescription(badgeDto.getDescription());
            }
            if (badgeDto.isCriteria() != badge.isCriteria()) {
                badge.setCriteria(badgeDto.isCriteria());
            }
            return badgeRepository.save(badge);
        } else {
            throw new RuntimeException("Badge not found with id " + id);
        }
    }

    public List<Badge> getAllBadges() {
        return badgeRepository.findAll();
    }
    public Optional<Badge> getBadgeById(Long id) {
        return badgeRepository.findById(id);
    }
}
