package Hackerton.CRUD.Repository;

import Hackerton.CRUD.domain.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
}
