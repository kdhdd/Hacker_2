package Hackerton.CRUD.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Hackerton.CRUD.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByLoginId(String loginId);
    boolean existsByNickname(String nickname);
    Optional<Member> findByLoginId(String loginId);
}