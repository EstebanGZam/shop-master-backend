package com.shop_master_backend.dto.shoppingCart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartProductDTO {
    private String productId;
    private String productName;
    private String size;
    private Integer quantity;
    private Double price;
    private Double total;
    private String imageUrl;
}
