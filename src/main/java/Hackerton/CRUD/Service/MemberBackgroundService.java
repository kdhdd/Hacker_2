//package Hackerton.CRUD.Service;
//
//import Hackerton.CRUD.Repository.MemberBackgroundRepository;
//import Hackerton.CRUD.Repository.BackgroundRepository;
//import Hackerton.CRUD.Repository.MemberRepository;
//import Hackerton.CRUD.domain.MemberBackground;
//import Hackerton.CRUD.domain.Background;
//import Hackerton.CRUD.domain.Member;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class MemberBackgroundService {
//
//    private final MemberBackgroundRepository memberBackgroundRepository;
//    private final BackgroundRepository backgroundRepository;
//    private final MemberRepository memberRepository;
//    private final CoinService coinService;
//
//    @Transactional
//    public void purchaseBackground(Long memberId, Long backgroundId) {
//        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member not found"));
//        Background background = backgroundRepository.findById(backgroundId).orElseThrow(() -> new IllegalArgumentException("Background not found"));
//
//        if (background.getBackgroundPointCost() > 0) {
//            coinService.subtractCoins(memberId, background.getBackgroundPointCost(), "Purchase background");
//        }
//
//        MemberBackground memberBackground = new MemberBackground(member, background);
//        memberBackgroundRepository.save(memberBackground);
//    }
//
//    public List<MemberBackground> getPurchasedBackgrounds(Long memberId) {
//        return memberBackgroundRepository.findByMemberId(memberId);
//    }
//}
