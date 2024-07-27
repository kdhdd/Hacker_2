package Hackerton.CRUD.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Font {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "font_id")
    private Long id;

    private String fontName;
    private Integer fontPointCost;

    @OneToMany(mappedBy = "font")
    private List<PostFont> postFonts;

//    //다대다 조인
//    @ManyToMany(mappedBy = "fonts")
//    private List<Post> posts;
}

