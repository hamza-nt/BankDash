package com.bankdash.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferDTO {
    private String id;
    private Double amount;
    private LocalDateTime date;
    private String status;
    private String message;
    private String receiverId;
    private String senderId;
}
