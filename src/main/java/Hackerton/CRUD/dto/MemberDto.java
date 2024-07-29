package Hackerton.CRUD.dto;

import lombok.Getter;
import lombok.Setter;
import Hackerton.CRUD.domain.Member;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberDto {
    private Long id;
    private String Member_Name;
    private String Member_EMail;
    private String Member_Password;
    private Long coins;
    private String Badges;
    private boolean Today;


    public static MemberDto from(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setMember_Name(member.getNickname());
        memberDto.setMember_EMail(member.getLoginId());
        memberDto.setMember_Password(member.getPassword());
        memberDto.setCoins(member.getCoins());
        memberDto.setBadges(member.getBadges());

        memberDto.setToday(member.isToday());
        return memberDto;
    }
}
