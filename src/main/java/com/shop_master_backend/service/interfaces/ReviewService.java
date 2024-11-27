package com.shop_master_backend.service.interfaces;

import com.shop_master_backend.dto.review.ReviewRequestDTO;
import com.shop_master_backend.dto.review.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {
	ReviewResponseDTO addReview(ReviewRequestDTO reviewRequestDTO);

	List<ReviewResponseDTO> getReviewsByProductId(String productId);

	List<ReviewResponseDTO> getReviewsByRatingRange(Double minRating, Double maxRating);
}
