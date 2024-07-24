package Hackerton.CRUD.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Team {
    @Id // 기본키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    // 그룹에 배정된 주제
    private String topic; // 주제의 첫 글자를 소문자로 변경

    @CreationTimestamp
    private LocalDateTime Created_at;

    @OneToMany(mappedBy = "team")
    private List<Post> posts; // Post와 연관관계 설정
}
