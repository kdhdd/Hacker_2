package Hackerton.CRUD.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Group {
    @Id // 기본키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    // 그룹에 배정된 주제
    private String Topic;

    @CreationTimestamp
    private LocalDateTime Created_at;

    /*@OneToMany(mappedBy = "post")
    private List<Post> posts = new ArrayList<>();*/
}
