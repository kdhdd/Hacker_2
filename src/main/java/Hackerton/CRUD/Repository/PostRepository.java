package Hackerton.CRUD.Repository;

import Hackerton.CRUD.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByMemberIdAndTeamIdAndCreatedAtBetween(Long memberId, Long teamId, LocalDateTime startOfDay, LocalDateTime endOfDay);
}

//findByMemberIdAndGroupIdAndCreatedAtBetween

