package Hackerton.CRUD.Repository;

import Hackerton.CRUD.domain.MemberFont;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberFontRepository extends JpaRepository<MemberFont, Long> {
    List<MemberFont> findByMemberId(Long memberId);
}