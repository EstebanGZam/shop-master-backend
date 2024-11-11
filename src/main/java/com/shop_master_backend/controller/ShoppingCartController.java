package com.shop_master_backend.controller;

import com.shop_master_backend.dto.shoppingCart.ProductToCartRequestDTO;
import com.shop_master_backend.dto.shoppingCart.ShoppingCartResponseDTO;
import com.shop_master_backend.entity.sql.User;
import com.shop_master_backend.service.interfaces.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public ShoppingCartResponseDTO addProductToCart(
            @RequestBody ProductToCartRequestDTO productToCartRequestDTO,
            @AuthenticationPrincipal User user) {

        return shoppingCartService.addProductToCart(productToCartRequestDTO, user.getUsername());
    }

    @PutMapping("/update")
    public ShoppingCartResponseDTO updateCart(
            @RequestBody ProductToCartRequestDTO updateProductToCartRequestDTO,
            @AuthenticationPrincipal User user) {

        return shoppingCartService.updateProductQuantityInCart(updateProductToCartRequestDTO, user.getUsername());
    }

    @DeleteMapping("/remove/{productId}")
    public ShoppingCartResponseDTO removeProductFromCart(
            @PathVariable String productId,
            @AuthenticationPrincipal User user) {

        return shoppingCartService.removeProductFromCart(productId, user.getUsername());
    }

    @GetMapping
    public ShoppingCartResponseDTO getCart(@AuthenticationPrincipal User user) {
        return shoppingCartService.getCart(user.getUsername());
    }

}
