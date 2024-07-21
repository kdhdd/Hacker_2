package Hackerton.CRUD.Repository;

import Hackerton.CRUD.domain.MemberQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberQuestionRepository extends JpaRepository<MemberQuestion, Long> {
}