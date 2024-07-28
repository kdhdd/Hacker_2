package Hackerton.CRUD.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "emotionId")
    private Emotion emotion;

    private String content;
    private LocalDateTime createdAt;
    private String title;
    //private Integer HeartCount;
    private String tempSave;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Heart> hearts = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostFont> postFonts = new ArrayList<>();

//    //Post Table을 참조하는 컬럼명을 post_id
//    //Font Table을 참조하는 컬럼명을 font_id로 지칭
//    @ManyToMany
//    @JoinTable(
//            name = "post_font",
//            joinColumns = @JoinColumn(name = "post_id"),
//            inverseJoinColumns = @JoinColumn(name = "font_id")
//    )
//    private List<Font> fonts;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostBackground> postBackgrounds = new ArrayList<>();

//    //Post Table을 참조하는 컬럼명을 post_id
//    //background Table을 참조하는 컬럼명을 background_id로 지칭
//    @ManyToMany
//    @JoinTable(
//            name = "post_background",
//            joinColumns = @JoinColumn(name = "post_id"),
//            inverseJoinColumns = @JoinColumn(name = "background_id")
//    )
//    private List<Background> backgrounds;

    // 좋아요 표시 갯수 파악
    //null 일 경우 0 반환, null이 아닐 경우 리스트 크기 반환
    public int getHeartCount() {
        return hearts != null ? hearts.size() : 0;
    }
    //하트 리스트
    //해당 게시물에 누가 하트를 눌렀는지 확인 가능
    public List<Heart> getHearts() {
        return hearts;
    }


}
