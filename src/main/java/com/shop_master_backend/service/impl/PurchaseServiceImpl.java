package com.shop_master_backend.service.impl;

import com.shop_master_backend.dto.purchase.InvoiceProductDTO;
import com.shop_master_backend.dto.purchase.InvoiceResponseDTO;
import com.shop_master_backend.dto.purchase.PurchaseRequestDTO;
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

    private final PurchaseMapper purchaseMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentDetailRepository paymentDetailRepository;
    private final InvoiceRepository invoiceRepository;
    private final ShoppingCartService shoppingCartService;

    @Override
    @Transactional
    public InvoiceResponseDTO makePurchase(PurchaseRequestDTO purchaseRequestDTO, String username) {
        // Obtener el usuario
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Obtener el carrito de compras del usuario y verificar si está vacío
        ShoppingCart cart = shoppingCartService.getCartEntity(username);
        if (cart.getShoppingCartProducts().isEmpty()) {
            throw new ShoppingCartNotFoundException("Shopping cart is empty");
        }

        // Verificar que todos los productos del carrito tengan suficiente stock
        cart.getShoppingCartProducts().forEach(cartProduct -> {
            Product product = productRepository.findById(cartProduct.getProduct())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));
            if (product.getStockQuantity() < cartProduct.getQuantity()) {
                throw new ShoppingCartNotFoundException("Insufficient stock for product: " + product.getName());
            }
        });

        // Disminuir la cantidad de productos del stock
        cart.getShoppingCartProducts().forEach(cartProduct -> {
            Product product = productRepository.findById(cartProduct.getProduct())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));
            product.setStockQuantity(product.getStockQuantity() - cartProduct.getQuantity());
            productRepository.save(product);
        });

        // Crear y guardar orden
        Order order = purchaseMapper.toOrder(cart, user);
        order = orderRepository.save(order);

        // Crear y guardar los detalles de la orden para cada producto en el carrito
        Order finalOrder = order;
        cart.getShoppingCartProducts().forEach(cartProduct -> {
            OrderDetail orderDetail = purchaseMapper.toOrderDetail(finalOrder, cartProduct);
            orderDetailRepository.save(orderDetail);
        });

        // Obtener el método de pago
        PaymentMethod paymentMethod = paymentMethodRepository.findById(purchaseRequestDTO.getPaymentDetail().getPaymentMethodId())
                .orElseThrow(() -> new PaymentMethodNotFoundException("Payment method not found"));

        // Crear y guardar los detalles del pago
        PaymentDetail paymentDetail = purchaseMapper.toPaymentDetail(purchaseRequestDTO.getPaymentDetail(), order);
        paymentDetail = paymentDetailRepository.save(paymentDetail);

        // Crear y guardar la factura
        Invoice invoice = purchaseMapper.toInvoice(order);
        invoice = invoiceRepository.save(invoice);

        // Limpiar el carrito de compras
        shoppingCartService.clearCart(username);

        // Obtener la factura
        return getInvoice(invoice.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public InvoiceResponseDTO getInvoice(Integer invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found"));

        // Cargar el detalle de la orden
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(invoice.getOrder().getId());

        // Mapeo de los productos de los detalles de la orden
        List<InvoiceProductDTO> products = orderDetails.stream()
                .map(orderDetail -> purchaseMapper.toInvoiceProductDTO(orderDetail))
                .collect(Collectors.toList());

        InvoiceResponseDTO invoiceResponseDTO = purchaseMapper.toInvoiceResponseDTO(invoice);
        invoiceResponseDTO.setProducts(products);
        return invoiceResponseDTO;
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
