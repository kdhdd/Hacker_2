package Hackerton.CRUD.Service;

import Hackerton.CRUD.Repository.MemberFontRepository;
import Hackerton.CRUD.Repository.FontRepository;
import Hackerton.CRUD.Repository.MemberRepository;
import Hackerton.CRUD.domain.MemberFont;
import Hackerton.CRUD.domain.Font;
import Hackerton.CRUD.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberFontService {

    private final MemberFontRepository memberFontRepository;
    private final FontRepository fontRepository;
    private final MemberRepository memberRepository;
    private final CoinService coinService;

    @Transactional
    public void purchaseFont(Long memberId, Long fontId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        Font font = fontRepository.findById(fontId).orElseThrow(() -> new IllegalArgumentException("Font not found"));

        if (font.getFontPointCost() > 0) {
            coinService.subtractCoins(memberId, font.getFontPointCost(), "Purchase font");
        }

        MemberFont memberFont = new MemberFont(member, font);
        memberFontRepository.save(memberFont);
    }

    public List<MemberFont> getPurchasedFonts(Long memberId) {
        return memberFontRepository.findByMemberId(memberId);
    }
}
