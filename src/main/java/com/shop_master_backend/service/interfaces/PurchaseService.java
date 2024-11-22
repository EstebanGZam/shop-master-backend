package com.shop_master_backend.service.interfaces;

import com.shop_master_backend.dto.purchase.InvoiceResponseDTO;
import com.shop_master_backend.dto.purchase.PurchaseRequestDTO;

import java.util.List;

public interface PurchaseService {

    InvoiceResponseDTO makePurchase(PurchaseRequestDTO purchaseRequestDTO, String username);

    InvoiceResponseDTO getInvoice(Integer invoiceId);

    List<InvoiceResponseDTO> getPurchaseHistory(String username);

}