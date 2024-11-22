package com.bankdash.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticDTO {
    private String id;
    private String category;
    private Double percentage;
    private Double amount;
    private String userId;
}
