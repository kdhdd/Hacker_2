//package Hackerton.CRUD.Service;
//
////import Hackerton.CRUD.Repository.MemberBackgroundRepository;
////import Hackerton.CRUD.Repository.BackgroundRepository;
//import Hackerton.CRUD.domain.Member;
////import Hackerton.CRUD.domain.MemberBackground;
//import Hackerton.CRUD.domain.Background;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class BackgroundPurchaseService {
////
////    private final MemberBackgroundRepository memberBackgroundRepository;
////    private final BackgroundRepository backgroundRepository;
//    private final CoinService coinService;
//
//    @Transactional
//    public void purchaseBackground(Long memberId, Long backgroundId) {
//        Background background = backgroundRepository.findById(backgroundId)
//                .orElseThrow(() -> new IllegalArgumentException("Background not found"));
//
//        if (background.getBackgroundPointCost() != null && background.getBackgroundPointCost() > 0) {
//            coinService.subtractCoins(memberId, background.getBackgroundPointCost(), "Background purchase");
//        }
//
//        MemberBackground memberBackground = new MemberBackground();
//        memberBackground.setBackground(background);
//        memberBackground.setMember(new Member());
//        memberBackground.getMember().setId(memberId);
//        memberBackgroundRepository.save(memberBackground);
//    }
//}
