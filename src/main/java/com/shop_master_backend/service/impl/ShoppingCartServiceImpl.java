package com.shop_master_backend.service.impl;

import com.shop_master_backend.dto.shoppingCart.ProductToCartRequestDTO;
import com.shop_master_backend.dto.shoppingCart.ShoppingCartResponseDTO;
import com.shop_master_backend.entity.sql.ShoppingCart;
import com.shop_master_backend.entity.sql.ShoppingCartProduct;
import com.shop_master_backend.entity.sql.User;
import com.shop_master_backend.exception.runtime.ProductNotFoundException;
import com.shop_master_backend.exception.runtime.ShoppingCartNotFoundException;
import com.shop_master_backend.exception.runtime.UserNotFoundException;
import com.shop_master_backend.mapper.shoppingCart.ShoppingCartMapper;
import com.shop_master_backend.repository.ShoppingCartProductRepository;
import com.shop_master_backend.repository.ShoppingCartRepository;
import com.shop_master_backend.repository.UserRepository;
import com.shop_master_backend.service.interfaces.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartProductRepository shoppingCartProductRepository;

    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    @Transactional
    public ShoppingCartResponseDTO addProductToCart(ProductToCartRequestDTO request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        ShoppingCart cart = shoppingCartRepository.findByUser(user)
                .orElseGet(() -> createNewCartForUser(user));

        Optional<ShoppingCartProduct> cartProductOptional = cart.getShoppingCartProducts().stream()
                .filter(cp -> cp.getProduct().equals(request.getProductId()))
                .findFirst();

        if (cartProductOptional.isPresent()) {
            ShoppingCartProduct cartProduct = cartProductOptional.get();
            cartProduct.setQuantity(cartProduct.getQuantity() + request.getQuantity());
        } else {
            ShoppingCartProduct cartProduct = ShoppingCartProduct.builder()
                    .product(request.getProductId())
                    .quantity(request.getQuantity())
                    .cart(cart)
                    .build();
            cart.getShoppingCartProducts().add(cartProduct);
        }

        shoppingCartRepository.save(cart);
        return shoppingCartMapper.toResponseDTO(cart);
    }

    private ShoppingCart createNewCartForUser(User user) {
        ShoppingCart newCart = ShoppingCart.builder()
                .user(user)
                .shoppingCartProducts(new ArrayList<>())
                .build();

        return shoppingCartRepository.save(newCart);
    }

    @Override
    @Transactional
    public ShoppingCartResponseDTO updateProductQuantityInCart(ProductToCartRequestDTO request, String username) {
        ShoppingCart cart = getUserCart(username);
        ShoppingCartProduct cartProduct = cart.getShoppingCartProducts().stream()
                .filter(cp -> cp.getProduct().equals(request.getProductId()))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found in cart"));

        cartProduct.setQuantity(cartProduct.getQuantity() + request.getQuantity());
        shoppingCartProductRepository.save(cartProduct);

        return shoppingCartMapper.toResponseDTO(cart);
    }

    @Override
    @Transactional
    public ShoppingCartResponseDTO removeProductFromCart(String productId, String username) {
        ShoppingCart cart = getUserCart(username);
        cart.getShoppingCartProducts().removeIf(cp -> cp.getProduct().equals(productId));
        shoppingCartRepository.save(cart);

        return shoppingCartMapper.toResponseDTO(cart);
    }

    @Override
    @Transactional(readOnly = true)
    public ShoppingCartResponseDTO getCart(String username) {
        ShoppingCart cart = getUserCart(username);

        return shoppingCartMapper.toResponseDTO(cart);
    }

    private ShoppingCart getUserCart(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return shoppingCartRepository.findByUser(user)
                .orElseThrow(() -> new ShoppingCartNotFoundException("Cart not found"));
    }

}
