package com.shop_master_backend.service.impl;

import com.shop_master_backend.dto.product.ProductRequestDTO;
import com.shop_master_backend.dto.product.ProductResponseDTO;
import com.shop_master_backend.entity.mongodb.Product;
import com.shop_master_backend.mapper.product.ProductMapper;
import com.shop_master_backend.repository.ProductRepository;
import com.shop_master_backend.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) {
        Product product = productMapper.toProduct(productRequestDTO);
        product = productRepository.save(product);
        return productMapper.toProductResponseDTO(product);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductResponseDTO)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO getProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.toProductResponseDTO(product);
    }

}
