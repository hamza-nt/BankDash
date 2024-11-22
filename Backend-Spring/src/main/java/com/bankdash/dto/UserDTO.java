package com.bankdash.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String id;
    private String username;
    private String email;
    private String role;
    private String profilePicture;
    private LocalDateTime createdAt;
}

