//package Hackerton.CRUD.domain;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//public class Font {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "font_id")
//    private Long id;
//
//    private String fontName;
//    private Integer fontPointCost;
//
//    @OneToMany(mappedBy = "font")
//    private List<PostFont> postFonts;
//
//    @OneToMany(mappedBy = "font")
//    private List<MemberFont> memberFonts;
//
//    //데이터를 자동으로 초기화
//    public Font(String fontName, Integer fontPointCost) {
//        this.fontName = fontName;
//        this.fontPointCost = fontPointCost;
//    }
//
//    public Font() {
//    }
//
//}
//
