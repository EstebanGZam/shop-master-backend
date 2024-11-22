package com.shop_master_backend.mapper.purchase;

import com.shop_master_backend.dto.purchase.InvoiceProductDTO;
import com.shop_master_backend.dto.purchase.InvoiceResponseDTO;
import com.shop_master_backend.dto.purchase.PaymentDetailDTO;
import com.shop_master_backend.entity.mongodb.Product;
import com.shop_master_backend.entity.sql.*;
import com.shop_master_backend.exception.runtime.ProductNotFoundException;
import com.shop_master_backend.repository.ProductRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PurchaseMapper {

    @Autowired
    private ProductRepository productRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "total", expression = "java(calculateTotal(cart))")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "orderDetails", ignore = true)
    public abstract Order toOrder(ShoppingCart cart, User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", source = "shoppingCartProduct.product")
    @Mapping(target = "unitPrice", expression = "java(getProductPrice(shoppingCartProduct.getProduct()))")
    public abstract OrderDetail toOrderDetail(Order order, ShoppingCartProduct shoppingCartProduct);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "paymentMethod.id", source = "paymentDetailDTO.paymentMethodId")
    public abstract PaymentDetail toPaymentDetail(PaymentDetailDTO paymentDetailDTO, Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", source = "order")
    public abstract Invoice toInvoice(Order order);

    @Mapping(target = "invoiceId", source = "id")
    @Mapping(target = "products", source = "order.orderDetails")
    public abstract InvoiceResponseDTO toInvoiceResponseDTO(Invoice invoice);

    public InvoiceProductDTO toInvoiceProductDTO(OrderDetail orderDetail) {
        Product product = productRepository.findById(orderDetail.getProduct())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        return InvoiceProductDTO.builder()
                .productId(orderDetail.getProduct())
                .productName(product.getName())
                .quantity(orderDetail.getQuantity())
                .price(orderDetail.getUnitPrice())
                .total(orderDetail.getUnitPrice() * orderDetail.getQuantity())
                .imageUrl(product.getImage().getUrl())
                .build();
    }

    // Método auxiliar para calcular el total del carrito
    protected double calculateTotal(ShoppingCart cart) {
        return cart.getShoppingCartProducts().stream()
                .mapToDouble(cartProduct -> {
                    Product product = productRepository.findById(cartProduct.getProduct())
                            .orElseThrow(() -> new ProductNotFoundException("Product not found"));
                    return cartProduct.getQuantity() * product.getPrice();
                })
                .sum();
    }

    // Método auxiliar para obtener el precio de un producto
    protected double getProductPrice(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"))
                .getPrice();
    }

}
