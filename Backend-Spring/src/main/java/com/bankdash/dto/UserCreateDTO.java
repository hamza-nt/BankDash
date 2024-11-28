package com.bankdash.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDTO {

    private String id;
    private String username;
    private String password;
    private String email;
    private String role;
    private String profilePicture;
    private LocalDateTime createdAt;
}
