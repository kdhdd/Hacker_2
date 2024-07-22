package Hackerton.CRUD.Controller;

import Hackerton.CRUD.Service.BadgeService;
import Hackerton.CRUD.Service.MemberBadgeService;
import Hackerton.CRUD.Service.MemberService;
import Hackerton.CRUD.domain.MemberBadge;
import Hackerton.CRUD.dto.BadgeDto;
import Hackerton.CRUD.dto.MemberBadgeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/memberBadges")
public class MemberBadgeController {

    private final MemberBadgeService memberBadgeService;
    private final MemberService memberService;
    private final BadgeService badgeService;

    @Autowired
    public MemberBadgeController(MemberBadgeService memberBadgeService, MemberService memberService, BadgeService badgeService) {
        this.memberBadgeService = memberBadgeService;
        this.memberService = memberService;
        this.badgeService = badgeService;
    }

    @PostMapping
    public ResponseEntity<MemberBadgeDto> registerMemberBadge(@RequestBody MemberBadgeDto memberBadgeDto) {
        MemberBadge memberBadge = new MemberBadge();
        memberBadge.setMember(memberService.getMemberById(memberBadgeDto.getMemberId()).orElse(null));
        memberBadge.setBadge(badgeService.getBadgeById(memberBadgeDto.getBadgeId()).orElse(null));

        MemberBadge registeredMemberBadge = memberBadgeService.registerMemberBadge(memberBadge);
        return ResponseEntity.ok(MemberBadgeDto.from(registeredMemberBadge));
    }

    @GetMapping
    public ResponseEntity<List<MemberBadgeDto>> getAllMemberBadges() {
        List<MemberBadge> memberBadges = memberBadgeService.getAllMemberBadges();
        List<MemberBadgeDto> memberBadgeDtos = new ArrayList<>();
        for (MemberBadge memberBadge : memberBadges) {
            MemberBadgeDto dto = MemberBadgeDto.from(memberBadge);
            memberBadgeDtos.add(dto);
        }
        return ResponseEntity.ok(memberBadgeDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemberBadge(@PathVariable(name="id") Long id) {
        memberBadgeService.deleteMemberBadge(id);
        return ResponseEntity.noContent().build();
    }
}
