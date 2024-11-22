package com.bankdash.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO {
    private String id;
    private String transactionType;
    private Double amount;
    private LocalDateTime date;
    private String description;
    private String source;
    private String userId;
    private String cardId;
}

