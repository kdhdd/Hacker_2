package Hackerton.CRUD.Service;

import Hackerton.CRUD.Repository.MemberBadgeRepository;
import Hackerton.CRUD.domain.MemberBadge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MemberBadgeService {
    private final MemberBadgeRepository memberBadgeRepository;

    @Autowired
    public MemberBadgeService(MemberBadgeRepository memberBadgeRepository) {
        this.memberBadgeRepository = memberBadgeRepository;
    }
    @Transactional
    public MemberBadge registerMemberBadge(MemberBadge memberBadge) {
        return memberBadgeRepository.save(memberBadge);
    }

    @Transactional
    public void deleteMemberBadge(Long id) {
        memberBadgeRepository.deleteById(id);
    }

    // 수정할 값이 없음

    public List<MemberBadge> getAllMemberBadges() {
        return memberBadgeRepository.findAll();
    }
}
