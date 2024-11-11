package com.shop_master_backend.mapper.shoppingCart;

import com.shop_master_backend.dto.shoppingCart.ShoppingCartProductDTO;
import com.shop_master_backend.dto.shoppingCart.ShoppingCartResponseDTO;
import com.shop_master_backend.entity.mongodb.Product;
import com.shop_master_backend.entity.sql.ShoppingCart;
import com.shop_master_backend.entity.sql.ShoppingCartProduct;
import com.shop_master_backend.exception.runtime.ProductNotFoundException;
import com.shop_master_backend.repository.ProductRepository;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ShoppingCartMapper {

    @Autowired
    private ProductRepository productRepository;

    @Mapping(target = "products", source = "shoppingCartProducts")
    @Mapping(target = "totalPrice", expression = "java(calculateTotalPrice(cart.getShoppingCartProducts()))")
    public abstract ShoppingCartResponseDTO toResponseDTO(ShoppingCart cart);

    public ShoppingCartProductDTO toShoppingCartProductDTO(ShoppingCartProduct shoppingCartProduct) {
        Product product = productRepository.findById(shoppingCartProduct.getProduct())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        return ShoppingCartProductDTO.builder()
                .productId(product.getId())
                .productName(product.getName())
                .size(product.getSize().getName())
                .quantity(shoppingCartProduct.getQuantity())
                .price(product.getPrice())
                .total(product.getPrice() * shoppingCartProduct.getQuantity())
                .build();
    }

    protected Double calculateTotalPrice(List<ShoppingCartProduct> shoppingCartProducts) {
        return shoppingCartProducts.stream()
                .mapToDouble(cartProduct -> {
                    Product product = productRepository.findById(cartProduct.getProduct()).orElse(null);
                    return (product != null) ? product.getPrice() * cartProduct.getQuantity() : 0.0;
                })
                .sum();
    }

    @IterableMapping(elementTargetType = ShoppingCartProductDTO.class)
    protected List<ShoppingCartProductDTO> mapShoppingCartProducts(List<ShoppingCartProduct> shoppingCartProducts) {
        return shoppingCartProducts.stream()
                .map(this::toShoppingCartProductDTO)
                .collect(Collectors.toList());
    }

}
