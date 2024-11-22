package com.bankdash.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {
    @Id
    private String id;

    private String cardNumber;
    private String cardHolder;
    private Double balance;
    private LocalDateTime validThru;
    private String type;
    private LocalDateTime createdAt;
    private String userId;
}
