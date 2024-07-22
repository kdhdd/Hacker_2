package Hackerton.CRUD.Repository;

import Hackerton.CRUD.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
