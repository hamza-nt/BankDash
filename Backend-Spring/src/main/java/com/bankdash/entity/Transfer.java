package com.bankdash.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "transfers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transfer {
    @Id
    private String id;

    private Double amount;
    private LocalDateTime date;
    private String status;
    private String message;
    private String receiverId;
    private String senderId;
}
