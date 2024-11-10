package com.shop_master_backend.service;

import com.shop_master_backend.dto.product.ProductDTO;
import com.shop_master_backend.entity.mongodb.Product;
import com.shop_master_backend.entity.mongodb.Size;
import com.shop_master_backend.entity.mongodb.Category;
import com.shop_master_backend.entity.mongodb.Image;
import com.shop_master_backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setCreationDate(LocalDate.now());
        product.setSize(new Size(productDTO.getSize().getId(), productDTO.getSize().getName()));
        product.setCategory(new Category(productDTO.getCategory().getId(), productDTO.getCategory().getName(), productDTO.getCategory().getDescription()));
        product.setImage(new Image(productDTO.getImage().getId(), productDTO.getImage().getContent()));
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }
}