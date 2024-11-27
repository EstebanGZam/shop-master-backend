package com.shop_master_backend.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDTO {
    private String id;
    private Double rating;
    private String comment;
    private LocalDateTime date;
    private String productId;
    private Integer userId;
}