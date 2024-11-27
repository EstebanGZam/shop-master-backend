package com.shop_master_backend.service.impl;

import com.shop_master_backend.dto.review.ReviewRequestDTO;
import com.shop_master_backend.dto.review.ReviewResponseDTO;
import com.shop_master_backend.entity.mongodb.Review;
import com.shop_master_backend.mapper.review.ReviewMapper;
import com.shop_master_backend.repository.ReviewRepository;
import com.shop_master_backend.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
	private final ReviewRepository reviewRepository;
	private final ReviewMapper reviewMapper;

	@Override
	public ReviewResponseDTO addReview(ReviewRequestDTO reviewRequestDTO) {
		Review review = reviewMapper.toReview(reviewRequestDTO);
		review = reviewRepository.save(review);
		return reviewMapper.toReviewResponseDTO(review);
	}

	@Override
	public List<ReviewResponseDTO> getReviewsByProductId(String productId) {
		return reviewRepository.findByProductId(productId).stream()
				.map(reviewMapper::toReviewResponseDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<ReviewResponseDTO> getReviewsByRatingRange(Double minRating, Double maxRating) {
		// Validar que los valores de rango sean correctos
		if (minRating < 0.0 || maxRating > 5.0 || minRating > maxRating) {
			throw new IllegalArgumentException("Invalid rating range. Ensure 0.0 <= minRating <= maxRating <= 5.0");
		}
		List<Review> reviews = reviewRepository.findByRatingBetween(minRating, maxRating);
		// Consultar reseñas en el rango especificado
		return reviews.stream().map(reviewMapper::toReviewResponseDTO).toList();
	}

}
