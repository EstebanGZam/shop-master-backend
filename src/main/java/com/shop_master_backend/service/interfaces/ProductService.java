package com.shop_master_backend.service.interfaces;

import com.shop_master_backend.dto.product.ProductRequestDTO;
import com.shop_master_backend.dto.product.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO getProductById(String id);

}
