//package Hackerton.CRUD.domain;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//@IdClass(PostFontId.class)
//public class PostFont {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "post_font_id")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "post_id")
//    private Post post;
//
//    @ManyToOne
//    @JoinColumn(name = "font_id")
//    private Font font;
//}
