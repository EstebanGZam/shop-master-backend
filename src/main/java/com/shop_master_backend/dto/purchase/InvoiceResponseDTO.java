package com.shop_master_backend.dto.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponseDTO {
    private Integer invoiceId;
    private LocalDateTime issueDate;
    private Double total;
    private List<InvoiceProductDTO> products;
}