package com.bankdash.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    private String id;

    private String transactionType;
    private Double amount;
    private LocalDateTime date;
    private String description;
    private String source;
    private String userId;
    private String cardId;
}
