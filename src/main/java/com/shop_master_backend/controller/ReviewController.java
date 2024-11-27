package com.shop_master_backend.controller;

import com.shop_master_backend.dto.review.ReviewRequestDTO;
import com.shop_master_backend.dto.review.ReviewResponseDTO;
import com.shop_master_backend.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
	private final ReviewService reviewService;

	@PostMapping
	public ResponseEntity<ReviewResponseDTO> addReview(@RequestBody ReviewRequestDTO reviewRequestDTO) {
		ReviewResponseDTO review = reviewService.addReview(reviewRequestDTO);
		return ResponseEntity.ok(review);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<List<ReviewResponseDTO>> getReviewsByProductId(@PathVariable String productId) {
		List<ReviewResponseDTO> reviews = reviewService.getReviewsByProductId(productId);
		return ResponseEntity.ok(reviews);
	}

	@GetMapping("/filter-by-rating")
	public ResponseEntity<?> filterReviewsByRating(
			@RequestParam(defaultValue = "0.0") Double minRating,
			@RequestParam(defaultValue = "5.0") Double maxRating) {
		try {
			// Obtener reseñas filtradas
			List<ReviewResponseDTO> reviews = reviewService.getReviewsByRatingRange(minRating, maxRating);
			return ResponseEntity.ok(reviews);
		} catch (IllegalArgumentException ex) {
			// Manejar rango inválido
			return ResponseEntity.badRequest().body(ex.getMessage());
		} catch (Exception ex) {
			// Manejar errores inesperados
			return ResponseEntity.internalServerError()
					.body("An unexpected error occurred");
		}
	}
}
