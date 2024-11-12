package com.shop_master_backend.dto.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailDTO {
    private Integer cardNumber;
    private String cardholderName;
    private String cardholderLastname;
    private String securityCode;
    private LocalDateTime expirationDate;
    private Integer paymentMethodId;
}