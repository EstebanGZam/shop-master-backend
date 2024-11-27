package com.shop_master_backend.repository;

import com.shop_master_backend.entity.mongodb.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {
	List<Review> findByProductId(String productId);

	@Query("{'rating': { $gte: ?0, $lte: ?1 }}")
	List<Review> findByRatingBetween(Double minRating, Double maxRating);
}