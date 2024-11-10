package com.shop_master_backend.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private String id;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private LocalDateTime creationDate;
    private String sizeName;
    private String categoryName;
    private String imageUrl;
}
