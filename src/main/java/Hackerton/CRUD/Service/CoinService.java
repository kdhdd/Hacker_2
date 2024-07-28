package Hackerton.CRUD.Service;

import Hackerton.CRUD.Repository.CoinRepository;
import Hackerton.CRUD.Repository.CoinTransactionRepository;
import Hackerton.CRUD.Repository.MemberRepository;
import Hackerton.CRUD.domain.Coin;
import Hackerton.CRUD.domain.CoinTransaction;
import Hackerton.CRUD.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CoinService {
    private final CoinRepository coinRepository;
    private final MemberRepository memberRepository;
    private final CoinTransactionRepository coinTransactionRepository;


    @Autowired
    public CoinService(CoinRepository coinRepository, MemberRepository memberRepository) {
        this.coinRepository = coinRepository;
        this.memberRepository = memberRepository;
    }

    // 코인 추가
    @Transactional
    public void addCoins(Long memberId, int amount, String description) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        member.setCoins(member.getCoins() + amount);

        CoinTransaction transaction = new CoinTransaction();
        transaction.setMember(member);
        transaction.setChangeAmount(amount);
        transaction.setDescription(description);
        transaction.setCreatedAt(LocalDateTime.now());
        coinTransactionRepository.save(transaction);
    }

    // 코인 차감
    @Transactional
    public void subtractCoins(Long memberId, int amount, String description) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        if (member.getCoins() < amount) {
            throw new IllegalStateException("Not enough coins");
        }
        member.setCoins(member.getCoins() - amount);

        CoinTransaction transaction = new CoinTransaction();
        transaction.setMember(member);
        transaction.setChangeAmount(-amount);
        transaction.setDescription(description);
        transaction.setCreatedAt(LocalDateTime.now());
        coinTransactionRepository.save(transaction);
    }

    // 코인 조회
    @Transactional(readOnly = true)
    public int getCoinBalance(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        return member.getCoins().intValue();
    }
}