package com.shop_master_backend.repository;

import com.shop_master_backend.entity.mongodb.Category;
import com.shop_master_backend.entity.mongodb.Product;
import com.shop_master_backend.entity.mongodb.Size;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
	Optional<Product> findById(String id);

	// Method to filter products by size and availability
	List<Product> findBySizeAndStockQuantityGreaterThan(Size size, Integer stockQuantity);

	List<Product> findByCategory(Category category);
}