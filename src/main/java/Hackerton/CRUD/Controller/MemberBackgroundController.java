package Hackerton.CRUD.Controller;

import Hackerton.CRUD.Service.MemberBackgroundService;
import Hackerton.CRUD.domain.MemberBackground;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member-backgrounds")
@RequiredArgsConstructor
public class MemberBackgroundController {

    private final MemberBackgroundService memberBackgroundService;

    @PostMapping("/purchase")
    public void purchaseBackground(@RequestParam Long memberId, @RequestParam Long backgroundId) {
        memberBackgroundService.purchaseBackground(memberId, backgroundId);
    }

    @GetMapping("/{memberId}")
    public List<MemberBackground> getPurchasedBackgrounds(@PathVariable Long memberId) {
        return memberBackgroundService.getPurchasedBackgrounds(memberId);
    }
}
