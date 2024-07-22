package Hackerton.CRUD.dto;

import Hackerton.CRUD.domain.Badge;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadgeDto {
    private Long id;
    private String Badge_name;
    private String Description;
    private boolean Criteria;

    public static BadgeDto from(Badge badge) {
        BadgeDto badgeDto = new BadgeDto();
        badgeDto.setId(badge.getId());
        badgeDto.setBadge_name(badge.getBadge_name());
        badgeDto.setDescription(badge.getDescription());

        badgeDto.setCriteria(badge.isCriteria());
        return badgeDto;
    }
}
