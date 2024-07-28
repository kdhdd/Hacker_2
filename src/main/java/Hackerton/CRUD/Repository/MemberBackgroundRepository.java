package Hackerton.CRUD.Repository;

import Hackerton.CRUD.domain.MemberBackground;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberBackgroundRepository extends JpaRepository<MemberBackground, Long> {
    List<MemberBackground> findByMemberId(Long memberId);
}