package Hackerton.CRUD.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Hackerton.CRUD.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
