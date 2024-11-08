package com.shop_master_backend.entity.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDate;

@Data
@Document(collection = "reviews")
public class Review {
    @Id
    private String id;
    private String productId;
    private Integer userId;
    private Integer rating;
    private String comment;
    private LocalDate date;
}