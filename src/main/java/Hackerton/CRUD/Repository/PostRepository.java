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
    // 하루에 한 번만 글을 작성할 수 있음
    Optional<Post> findByMemberIdAndQuestionIdAndCreatedAtBetween(Long memberId, Long questionId, LocalDateTime startOfDay, LocalDateTime endOfDay);

    // 질문 ID로 모든 게시물을 생성일시 내림차순으로 정렬하여 찾음 (최신순 관련)
    @Query("SELECT p FROM Post p WHERE p.question.id = :questionId ORDER BY p.createdAt DESC")
    List<Post> findByQuestionIdOrderByCreatedAtDesc(Long questionId);

    // 질문 ID로 모든 게시물을 좋아요 수 내림차순으로 정렬하여 찾음 (인기글 관련)
    @Query("SELECT p FROM Post p LEFT JOIN p.hearts h WHERE p.question.id = :questionId GROUP BY p.id ORDER BY COUNT(h) DESC")
    List<Post> findByQuestionIdOrderByHeartCountDesc(Long questionId);


    // 질문 ID와 감정 ID로 특정 감정을 가진 모든 게시물을 찾음
    @Query("SELECT p FROM Post p WHERE p.question.id = :questionId AND p.emotion.id = :emotionId")
    List<Post> findByQuestionIdAndEmotionId(Long questionId, Long emotionId);

    // 질문 ID와 감정 ID로 특정 감정을 가진 모든 게시물을 생성일시 내림차순으로 정렬하여 찾음 (해당 감정의 최신순 관련)
    @Query("SELECT p FROM Post p WHERE p.question.id = :questionId AND p.emotion.id = :emotionId ORDER BY p.createdAt DESC")
    List<Post> findByQuestionIdAndEmotionIdOrderByCreatedAtDesc(Long questionId, Long emotionId);

    // 질문 ID와 감정 ID로 특정 감정을 가진 모든 게시물을 좋아요 수 내림차순으로 정렬하여 찾음 (해당 감정의 인기글 관련)
    @Query("SELECT p FROM Post p LEFT JOIN p.hearts h WHERE p.question.id = :questionId AND p.emotion.id = :emotionId GROUP BY p.id ORDER BY COUNT(h) DESC")
    List<Post> findByQuestionIdAndEmotionIdOrderByHeartCountDesc(Long questionId, Long emotionId);

    // 좋아요 수가 많은 상위 3개의 게시물을 찾음
    @Query("SELECT p FROM Post p ORDER BY p.heartCount DESC")
    List<Post> findTop3ByOrderByHeartCountDesc();
}

//findByMemberIdAndGroupIdAndCreatedAtBetween

