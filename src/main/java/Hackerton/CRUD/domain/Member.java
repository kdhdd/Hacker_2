package Hackerton.CRUD.domain;

import Hackerton.CRUD.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // 테이블과 엔티티 매핑
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id // 기본키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 기본키의 값을 생성할 전략을 지정
    private Long id;

    private String nickname;
    private String loginId;
    private String password;

    private UserRole role;

    private Long Member_Coins;
    private String Badges;

    @Builder.Default
    private boolean Today = false;

    @CreationTimestamp // 현재 시간으로 타임스탬프 생성
    private LocalDateTime registerDate;

    @Builder.Default
    @OneToMany(mappedBy="member")
    private List<Comment> comments = new ArrayList<>();

}
