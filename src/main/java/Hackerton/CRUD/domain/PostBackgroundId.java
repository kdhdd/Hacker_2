package Hackerton.CRUD.domain;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class PostBackgroundId implements Serializable {

    private Long post;
    private Long background;
}