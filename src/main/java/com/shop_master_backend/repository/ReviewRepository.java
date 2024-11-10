package com.shop_master_backend.repository;

import com.shop_master_backend.entity.mongodb.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {}