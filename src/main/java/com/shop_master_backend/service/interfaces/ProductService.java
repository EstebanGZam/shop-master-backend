package com.shop_master_backend.service.interfaces;

import com.shop_master_backend.dto.product.ProductRequestDTO;
import com.shop_master_backend.dto.product.ProductResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO, MultipartFile image);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO getProductById(String id);

    List<ProductResponseDTO> getProductsBySize(String size);

    List<ProductResponseDTO> getProductsByCategory(String categoryId);

    List<ProductResponseDTO> filterProducts(String size, String categoryId);
}
