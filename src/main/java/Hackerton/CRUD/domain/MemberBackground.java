//package Hackerton.CRUD.domain;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//public class MemberBackground {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "member_background_id")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;
//
//    @ManyToOne
//    @JoinColumn(name = "background_id")
//    private Background background;
//
//    public MemberBackground() {
//    }
//
//    public MemberBackground(Member member, Background background) {
//        this.member = member;
//        this.background = background;
//    }
//}
