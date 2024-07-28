package Hackerton.CRUD.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Background {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="background_id")
    private Long id;

    private String backgroundColor;
    private Integer backgroundPointCost;

    @OneToMany(mappedBy = "background")
    private List<PostBackground> postBackgrounds;

    @OneToMany(mappedBy = "background")
    private List<MemberBackground> memberBackgrounds;

    //데이터를 자동으로 초기화
    public Background(String backgroundColor, Integer backgroundPointCost) {
        this.backgroundColor = backgroundColor;
        this.backgroundPointCost = backgroundPointCost;
    }

    public Background() {
    }
}
