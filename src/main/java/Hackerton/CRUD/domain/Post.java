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
    @JoinColumn(name="question_id")
    private Question question;


//    @ManyToOne
//    @JoinColumn(name = "emotionId")
//    private Emotion emotion;

    private String content;
    private LocalDateTime createdAt;
    private String title;
    //private Integer HeartCount;
    private String tempSave;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Heart> hearts = new ArrayList<>();

//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
//    private List<PostFont> postFonts = new ArrayList<>();
//
//
//
//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
//    private List<PostBackground> postBackgrounds = new ArrayList<>();




    // 좋아요 표시 갯수 파악
    // null 일 경우 0 반환, null이 아닐 경우 리스트 크기 반환
    public int getHeartCount() {
        return hearts != null ? hearts.size() : 0;
    }
    /*
    해당 부분 구현해야 할지 몰라서 일단 주석처리 해둠..
    // 하트 리스트
    // 해당 게시물에 누가 하트를 눌렀는지 확인 가능
    public List<Heart> getHearts() {
        return hearts;
    }*/


}
