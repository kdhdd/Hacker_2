package Hackerton.CRUD.Repository;

import Hackerton.CRUD.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    //하루에 한 번만 글을 작성할 수 있음
    Optional<Post> findByMemberIdAndTeamIdAndCreatedAtBetween(Long memberId, Long teamId, LocalDateTime startOfDay, LocalDateTime endOfDay);

    // 팀 ID로 같은 주제의 모든 게시물을 찾음
    @Query("SELECT p FROM Post p WHERE p.team.id = :teamId")
    List<Post> findByTeamId(Long teamId);

    // 팀 ID로 모든 게시물을 생성일시 내림차순으로 정렬하여 찾음(최신순 관련)
    @Query("SELECT p FROM Post p WHERE p.team.id = :teamId ORDER BY p.createdAt DESC")
    List<Post> findByTeamIdOrderByCreatedAtDesc(Long teamId);

    // 팀 ID로 모든 게시물을 좋아요 수 내림차순으로 정렬하여 찾음 (인기글 관련)
    @Query("SELECT p FROM Post p LEFT JOIN p.hearts h GROUP BY p.id ORDER BY COUNT(h) DESC")
    List<Post> findByTeamIdOrderByHeartCountDesc(Long teamId);

    // 팀 ID와 감정 ID로 특정 감정을 가진 모든 게시물을 찾음
    @Query("SELECT p FROM Post p WHERE p.team.id = :teamId AND p.emotion.id = :emotionId")
    List<Post> findByTeamIdAndEmotionId(Long teamId, Long emotionId);

    // 팀 ID와 감정 ID로 특정 감정을 가진 모든 게시물을 생성일시 내림차순으로 정렬하여 찾음(해당 감정의 최신순 관련)
    @Query("SELECT p FROM Post p WHERE p.team.id = :teamId AND p.emotion.id = :emotionId ORDER BY p.createdAt DESC")
    List<Post> findByTeamIdAndEmotionIdOrderByCreatedAtDesc(Long teamId, Long emotionId);

    // 팀 ID와 감정 ID로 특정 감정을 가진 모든 게시물을 좋아요 수 내림차순으로 정렬하여 찾음(해당 감정의 인기글 관련)
    @Query("SELECT p FROM Post p LEFT JOIN p.hearts h WHERE p.team.id = :teamId AND p.emotion.id = :emotionId GROUP BY p.id ORDER BY COUNT(h) DESC")
    List<Post> findByTeamIdAndEmotionIdOrderByHeartCountDesc(Long teamId, Long emotionId);
}

//findByMemberIdAndGroupIdAndCreatedAtBetween

