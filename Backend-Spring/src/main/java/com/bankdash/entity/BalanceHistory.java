package com.bankdash.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "balance_histories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BalanceHistory {
    @Id
    private String id;

    private LocalDateTime date;
    private Double balance;
    private LocalDateTime createdAt;
    private String userId;
}
