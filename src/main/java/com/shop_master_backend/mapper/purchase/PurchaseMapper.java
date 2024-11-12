package com.shop_master_backend.mapper.purchase;

import com.shop_master_backend.dto.purchase.*;
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

    @Mapping(target = "orderDetails", source = "shoppingCartProducts")
    public abstract Order toOrder(ShoppingCart cart);

    @Mapping(target = "product", source = "product")
    public abstract OrderDetail toOrderDetail(ShoppingCartProduct shoppingCartProduct);

    @Mapping(target = "paymentMethod.id", source = "paymentMethodId")
    public abstract PaymentDetail toPaymentDetail(PaymentDetailDTO paymentDetailDTO);

    @Mapping(target = "products", source = "order.orderDetails")
    @Mapping(target = "invoiceId", source = "id")
    public abstract InvoiceResponseDTO toInvoiceResponseDTO(Invoice invoice);

    @Mapping(target = "productId", source = "product")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "unitPrice")
    @Mapping(target = "total", expression = "java(orderDetail.getUnitPrice() * orderDetail.getQuantity())")
    public InvoiceProductDTO toInvoiceProductDTO(OrderDetail orderDetail) {
        Product product = productRepository.findById(orderDetail.getProduct())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return InvoiceProductDTO.builder()
                .productId(orderDetail.getProduct())
                .productName(product.getName())
                .quantity(orderDetail.getQuantity())
                .price(orderDetail.getUnitPrice())
                .total(orderDetail.getUnitPrice() * orderDetail.getQuantity())
                .build();
    }
}