package Hackerton.CRUD.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CoinTransactionDto {
    private Long memberId;
    private Integer changeAmount;
    private String description;
    private LocalDateTime createdAt;
}