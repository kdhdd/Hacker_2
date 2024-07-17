package Hackerton.CRUD.Service;

import Hackerton.CRUD.Repository.CommentRepository;
import Hackerton.CRUD.domain.Comment;
import Hackerton.CRUD.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    @Transactional
    public Comment registerComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Transactional
    public Comment updateComment(Long id, CommentDto commentDto) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            if (commentDto.getContent() != null) {
                comment.setContent(commentDto.getContent());
            }
            return commentRepository.save(comment);
        } else {
            throw new RuntimeException("Comment not found with id " + id);
        }
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}