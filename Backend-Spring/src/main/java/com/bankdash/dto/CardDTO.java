package com.bankdash.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDTO {
    private String id;
    private String cardNumber;
    private String cardHolder;
    private Double balance;
    private LocalDateTime validThru;
    private String type;
    private LocalDateTime createdAt;
    private String userId;
}

