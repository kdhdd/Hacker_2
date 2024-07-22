package Hackerton.CRUD.dto;

import Hackerton.CRUD.domain.MemberBadge;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberBadgeDto {
    private Long id;
    private Long memberId;
    private Long badgeId;

    public static MemberBadgeDto from(MemberBadge memberBadge) {
        MemberBadgeDto memberBadgeDto = new MemberBadgeDto();
        memberBadgeDto.setId(memberBadgeDto.getId());
        if (memberBadge.getMember() != null) {
            memberBadgeDto.setMemberId(memberBadge.getMember().getId());
        }
        if (memberBadge.getBadge() != null) {
            memberBadgeDto.setBadgeId(memberBadge.getBadge().getId());
        }
        return memberBadgeDto;
    }
}
