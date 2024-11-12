package com.shop_master_backend.dto.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceProductDTO {
    private String productId;
    private String productName;
    private Integer quantity;
    private Double price;
    private Double total;
}