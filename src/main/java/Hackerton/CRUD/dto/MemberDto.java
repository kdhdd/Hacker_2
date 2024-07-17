package Hackerton.CRUD.dto;

import lombok.Getter;
import lombok.Setter;
import Hackerton.CRUD.domain.Member;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberDto {
    private Long id;
    private String User_Name;
    private String User_Psw;
    private String User_EMail;
    private String User_Nickname;
    private Long User_Coins;
    private String Badges;
    private LocalDateTime registerDate;


    public static MemberDto from(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setUser_Name(member.getUser_Name());
        memberDto.setUser_Psw(member.getUser_Psw());
        memberDto.setUser_EMail(member.getUser_EMail());
        memberDto.setUser_Nickname(member.getUser_Nickname());
        memberDto.setUser_Coins(member.getUser_Coins());
        memberDto.setBadges(member.getBadges());
        memberDto.setRegisterDate(member.getRegisterDate());
        return memberDto;
    }
}
