package com.shop_master_backend.dto.shoppingCart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductToCartRequestDTO {
    private String productId;
    private Integer quantity;
}
