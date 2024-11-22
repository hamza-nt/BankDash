package com.bankdash.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO {
    private String id;
    private String message;
    private String type;
    private LocalDateTime date;
    private Boolean readStatus;
    private String userId;
}
