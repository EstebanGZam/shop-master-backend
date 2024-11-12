package com.shop_master_backend.service.impl;

import com.shop_master_backend.dto.purchase.*;
import com.shop_master_backend.entity.mongodb.Product;
import com.shop_master_backend.entity.sql.*;
import com.shop_master_backend.exception.runtime.*;
import com.shop_master_backend.mapper.purchase.PurchaseMapper;
import com.shop_master_backend.repository.*;
import com.shop_master_backend.service.interfaces.PurchaseService;
import com.shop_master_backend.service.interfaces.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final InvoiceRepository invoiceRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartService shoppingCartService;
    private final PurchaseMapper purchaseMapper;

    @Override
    @Transactional
    public InvoiceResponseDTO makePurchase(PurchaseRequestDTO purchaseRequestDTO, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        ShoppingCart cart = shoppingCartService.getCartEntity(username);
        if (cart.getShoppingCartProducts().isEmpty()) {
            throw new ShoppingCartNotFoundException("Shopping cart is empty");
        }

        Order order = purchaseMapper.toOrder(cart);
        order.setUser(user);
        order.setTotal(cart.getShoppingCartProducts().stream()
                .mapToDouble(cartProduct -> {
                    Product product = productRepository.findById(cartProduct.getProduct())
                            .orElseThrow(() -> new ProductNotFoundException("Product not found"));
                    return cartProduct.getQuantity() * product.getPrice();
                })
                .sum());

        order.getOrderDetails().forEach(orderDetail -> {
            orderDetail.setOrder(order);
            Product product = productRepository.findById(orderDetail.getProduct())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));
            orderDetail.setUnitPrice(product.getPrice());
        });

        orderRepository.save(order);

        PaymentDetail paymentDetail = purchaseMapper.toPaymentDetail(purchaseRequestDTO.getPaymentDetail());
        paymentDetail.setOrder(order);

        Invoice invoice = Invoice.builder()
                .order(order)
                .total(order.getTotal())
                .build();

        invoiceRepository.save(invoice);

        shoppingCartService.clearCart(username);

        return purchaseMapper.toInvoiceResponseDTO(invoice);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceResponseDTO> getPurchaseHistory(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        List<Invoice> invoices = invoiceRepository.findByOrderUser(user);
        return invoices.stream()
                .map(purchaseMapper::toInvoiceResponseDTO)
                .collect(Collectors.toList());
    }
}