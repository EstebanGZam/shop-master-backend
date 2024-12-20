package com.shop_master_backend.service.interfaces;

import com.shop_master_backend.dto.shoppingCart.ProductToCartRequestDTO;
import com.shop_master_backend.dto.shoppingCart.ShoppingCartResponseDTO;
import com.shop_master_backend.entity.sql.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCartResponseDTO addProductToCart(ProductToCartRequestDTO requestDTO, String username);

    ShoppingCartResponseDTO updateProductQuantityInCart(ProductToCartRequestDTO requestDTO, String username);

    ShoppingCartResponseDTO removeProductFromCart(String productId, String username);

    ShoppingCartResponseDTO getCart(String username);

    ShoppingCart getCartEntity(String username);

    void clearCart(String username);
    
}
