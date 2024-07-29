//package Hackerton.CRUD.Service;
//
//import Hackerton.CRUD.Repository.MemberFontRepository;
//import Hackerton.CRUD.Repository.FontRepository;
//import Hackerton.CRUD.domain.Member;
//import Hackerton.CRUD.domain.MemberFont;
//import Hackerton.CRUD.domain.Font;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class FontPurchaseService {
//
//    private final MemberFontRepository memberFontRepository;
//    private final FontRepository fontRepository;
//    private final CoinService coinService;
//
//    @Transactional
//    public void purchaseFont(Long memberId, Long fontId) {
//        Font font = fontRepository.findById(fontId)
//                .orElseThrow(() -> new IllegalArgumentException("Font not found"));
//
//        if (font.getFontPointCost() != null && font.getFontPointCost() > 0) {
//            coinService.subtractCoins(memberId, font.getFontPointCost(), "Font purchase");
//        }
//
//        MemberFont memberFont = new MemberFont();
//        memberFont.setFont(font);
//        memberFont.setMember(new Member());
//        memberFont.getMember().setId(memberId);
//        memberFontRepository.save(memberFont);
//    }
//}
