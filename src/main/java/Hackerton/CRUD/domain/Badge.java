package Hackerton.CRUD.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="badge_id")
    private Long id;

    // 뱃지 이름
    private String Badge_name;
    // 뱃지에 대한 설명
    private String Description;
    // 뱃지를 획득했으면 true, 안 했으면 false
    @ColumnDefault("false")
    private boolean Criteria = false;

    @OneToMany(mappedBy = "badge")
    private List<MemberBadge> memberBadges = new ArrayList<>();

}
