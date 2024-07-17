package Hackerton.CRUD.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // 테이블과 엔티티 매핑
@Getter
@Setter
public class Member {
    @Id // 기본키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 기본키의 값을 생성할 전략을 지정
    @Column(name="member_id")
    private Long id;

    private String User_Psw;
    private String User_Name;
    private String User_EMail;
    private String User_Nickname;
    private Long User_Coins;
    private String Badges;

    @CreationTimestamp // 현재 시간으로 타임스탬프 생성
    private LocalDateTime registerDate;

    @OneToMany(mappedBy="member")
    private List<Comment> comments = new ArrayList<>();

}
