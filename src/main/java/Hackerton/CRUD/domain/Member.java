package Hackerton.CRUD.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
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

    private String Member_Name;
    private String Member_EMail;
    private String Member_Password;
    private Long Member_Coins;
    private String Badges;

    @ColumnDefault("true")
    private boolean Today = true;

    @CreationTimestamp // 현재 시간으로 타임스탬프 생성
    private LocalDateTime registerDate;

    @OneToMany(mappedBy="member")
    private List<Comment> comments = new ArrayList<>();

}

//    @ColumnDefault("true")
//    private boolean perform = true; // 수행 여부
