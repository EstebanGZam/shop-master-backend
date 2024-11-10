package com.shop_master_backend.repository;

import com.shop_master_backend.entity.mongodb.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, String> {}