package Hackerton.CRUD.Repository;

import Hackerton.CRUD.domain.Post;
import Hackerton.CRUD.domain.PostFont;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostFontRepository extends JpaRepository<PostFont, Long> {
    void deleteByPost(Post post);
}
