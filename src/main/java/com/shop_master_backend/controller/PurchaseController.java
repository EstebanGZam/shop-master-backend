package com.shop_master_backend.controller;

import com.shop_master_backend.dto.purchase.InvoiceResponseDTO;
import com.shop_master_backend.dto.purchase.PurchaseRequestDTO;
import com.shop_master_backend.entity.sql.User;
import com.shop_master_backend.service.interfaces.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<InvoiceResponseDTO> makePurchase(@RequestBody PurchaseRequestDTO purchaseRequestDTO,
                                                           @AuthenticationPrincipal User user) {
        InvoiceResponseDTO invoice = purchaseService.makePurchase(purchaseRequestDTO, user.getUsername());
        return ResponseEntity.ok(invoice);
    }

    @GetMapping("/order-history")
    public ResponseEntity<List<InvoiceResponseDTO>> getPurchaseHistory(@AuthenticationPrincipal User user) {
        List<InvoiceResponseDTO> response = purchaseService.getPurchaseHistory(user.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
