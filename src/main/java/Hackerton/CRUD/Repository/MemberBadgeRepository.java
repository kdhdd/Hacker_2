package Hackerton.CRUD.Repository;

import Hackerton.CRUD.domain.MemberBadge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberBadgeRepository extends JpaRepository<MemberBadge, Long> {
}
