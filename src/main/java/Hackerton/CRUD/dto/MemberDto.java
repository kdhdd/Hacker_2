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
    private Long Member_Coins;
    private String Badges;
    private boolean Today;


    public static MemberDto from(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setMember_Name(member.getMember_Name());
        memberDto.setMember_EMail(member.getMember_EMail());
        memberDto.setMember_Password(member.getMember_Password());
        memberDto.setMember_Coins(member.getMember_Coins());
        memberDto.setBadges(member.getBadges());

        memberDto.setToday(member.isToday());
        return memberDto;
    }
}
