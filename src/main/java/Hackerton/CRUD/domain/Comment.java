package Hackerton.CRUD.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {
    @Id // 기본키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 기본키의 값을 생성할 전략을 지정
    @Column(name="comment_id")
    private Long id;

    private String content;

    @CreationTimestamp // 현재 시간으로 타임스탬프 생성
    private LocalDateTime registerDate;

    @ManyToOne // 다대일 관계
    @JoinColumn(name="member_id")
    private Member member;
}