package com.bankdash.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BalanceHistoryDTO {
    private String id;
    private LocalDateTime date;
    private Double balance;
    private LocalDateTime createdAt;
    private String userId;
}
