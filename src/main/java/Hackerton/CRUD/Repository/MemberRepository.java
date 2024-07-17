package Hackerton.CRUD.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Hackerton.CRUD.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
