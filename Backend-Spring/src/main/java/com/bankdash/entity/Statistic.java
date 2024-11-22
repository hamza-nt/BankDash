package com.bankdash.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "statistics")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Statistic {
    @Id
    private String id;

    private String category;
    private Double percentage;
    private Double amount;
    private String userId;
}
