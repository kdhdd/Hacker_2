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
    private final CoinService coinService;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, CoinService coinService) {
        this.commentRepository = commentRepository;
        this.coinService = coinService;
    }
    @Transactional
    public Comment registerComment(Comment comment) {
        Comment savedComment = commentRepository.save(comment);

        // 댓글 작성 시 2코인 추가
        coinService.addCoins(comment.getMember().getId(), 2, "댓글 작성 보상");

        return savedComment;
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