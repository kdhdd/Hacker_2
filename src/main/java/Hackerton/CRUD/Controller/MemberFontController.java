package Hackerton.CRUD.Controller;

import Hackerton.CRUD.Service.MemberFontService;
import Hackerton.CRUD.domain.MemberFont;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member-fonts")
@RequiredArgsConstructor
public class MemberFontController {

    private final MemberFontService memberFontService;

    @PostMapping("/purchase")
    public void purchaseFont(@RequestParam Long memberId, @RequestParam Long fontId) {
        memberFontService.purchaseFont(memberId, fontId);
    }

    @GetMapping("/{memberId}")
    public List<MemberFont> getPurchasedFonts(@PathVariable Long memberId) {
        return memberFontService.getPurchasedFonts(memberId);
    }
}
