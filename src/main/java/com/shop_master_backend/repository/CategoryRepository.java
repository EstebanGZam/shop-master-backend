package com.shop_master_backend.repository;

import com.shop_master_backend.entity.mongodb.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {}