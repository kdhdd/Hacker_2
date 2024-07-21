package Hackerton.CRUD.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //다대다 조인
    @ManyToMany(mappedBy = "backgrounds")
    private List<Post> posts;


}
