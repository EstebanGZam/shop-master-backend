package com.shop_master_backend.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private String sizeId;
    private String categoryId;
}
