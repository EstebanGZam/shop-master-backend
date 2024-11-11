package com.shop_master_backend.dto.shoppingCart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartResponseDTO {
    private List<ShoppingCartProductDTO> products;
    private Double totalPrice;
}
