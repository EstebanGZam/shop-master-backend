package com.shop_master_backend.repository;

import com.shop_master_backend.entity.mongodb.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {}