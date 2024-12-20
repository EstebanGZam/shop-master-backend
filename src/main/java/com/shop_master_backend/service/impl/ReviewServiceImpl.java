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
    public ReviewResponseDTO addReview(Integer userId, ReviewRequestDTO reviewRequestDTO) {
        Review review = reviewMapper.toReview(reviewRequestDTO);
        review.setUserId(userId);
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
    public List<ReviewResponseDTO> getReviewsByRatingRangeAndProduct(Double minRating, Double maxRating, String productId) {
        // Validar los valores de rango y el ID del producto
        if (minRating < 0.0 || maxRating > 5.0 || minRating > maxRating) {
            throw new IllegalArgumentException("Invalid rating range. Ensure 0.0 <= minRating <= maxRating <= 5.0");
        }
        if (productId == null || productId.isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty.");
        }

        // Consultar reseñas filtradas por rango y producto
        List<Review> reviews = reviewRepository.findByRatingBetweenAndProductId(minRating, maxRating, productId);
        return reviews.stream().map(reviewMapper::toReviewResponseDTO).toList();
    }


}
