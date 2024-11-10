package com.shop_master_backend.repository;

import com.shop_master_backend.entity.mongodb.Size;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SizeRepository extends MongoRepository<Size, String> {}