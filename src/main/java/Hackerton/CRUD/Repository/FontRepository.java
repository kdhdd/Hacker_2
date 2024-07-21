package Hackerton.CRUD.Repository;

import Hackerton.CRUD.domain.Font;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FontRepository extends JpaRepository<Font, Long> {
}
