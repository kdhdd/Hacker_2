package Hackerton.CRUD.Controller;

import Hackerton.CRUD.Service.MemberService;
import Hackerton.CRUD.domain.Member;
import Hackerton.CRUD.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 가입
    @PostMapping
    public ResponseEntity<MemberDto> registerMember(@RequestBody MemberDto memberDto) {
        Member member = new Member();
        member.setMember_Name(memberDto.getMember_Name());
        member.setMember_EMail(memberDto.getMember_EMail());
        member.setMember_Password(memberDto.getMember_Password());
        member.setMember_Coins(memberDto.getMember_Coins());
        member.setBadges(memberDto.getBadges());
        Member registeredMember = memberService.registerMember(member);
        return ResponseEntity.ok(MemberDto.from(registeredMember));
    }

    // 모든 회원 조회
    @GetMapping
    public ResponseEntity<List<MemberDto>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        List<MemberDto> memberDtos = new ArrayList<>();
        for (Member member : members) {
            MemberDto dto = MemberDto.from(member);
            memberDtos.add(dto);
        }
        return ResponseEntity.ok(memberDtos);
    }

    // ID로 회원 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> getMemberById(@PathVariable(name="id") Long id) {
        Optional<Member> memberOptional = memberService.getMemberById(id);
        if (memberOptional.isPresent()) {
            MemberDto dto = MemberDto.from(memberOptional.get());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 회원 탈퇴
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    // 회원 정보 수정
    @PatchMapping("/{id}")
    public ResponseEntity<MemberDto> UpdateMember(@PathVariable Long id, @RequestBody MemberDto memberDto) {
        try {
            Member updatedMember = memberService.updateMember(id, memberDto);
            return ResponseEntity.ok(MemberDto.from(updatedMember));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
