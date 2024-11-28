package com.shop_master_backend.controller;

import com.shop_master_backend.constant.NotificationTopics;
import com.shop_master_backend.dto.review.ReviewRequestDTO;
import com.shop_master_backend.dto.review.ReviewResponseDTO;
import com.shop_master_backend.entity.sql.User;
import com.shop_master_backend.event.EntityUpdateEvent;
import com.shop_master_backend.service.interfaces.NotificationService;
import com.shop_master_backend.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final NotificationService notificationService;
    private final ApplicationEventPublisher eventPublisher;

    @GetMapping(path = "/stream/{productId}")
    public SseEmitter streamReviews(@PathVariable String productId) {
        SseEmitter emitter = notificationService.subscribe(NotificationTopics.REVIEWS);

        try {
            List<ReviewResponseDTO> reviews = reviewService.getReviewsByProductId(productId);
            emitter.send(SseEmitter.event()
                    .name(NotificationTopics.REVIEWS)
                    .data(reviews));
        } catch (IOException e) {
            emitter.completeWithError(e);
        }

        return emitter;
    }

    private void notifyProductUpdate(String productId) {
        List<ReviewResponseDTO> reviews = reviewService.getReviewsByProductId(productId);
        eventPublisher.publishEvent(new EntityUpdateEvent<>(NotificationTopics.REVIEWS, reviews));
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDTO> addReview(@AuthenticationPrincipal User user, @RequestBody ReviewRequestDTO reviewRequestDTO) {
        ReviewResponseDTO review = reviewService.addReview(user.getId(), reviewRequestDTO);

        // Notifica cualquier actualización en las reviews a los clientes
        notifyProductUpdate(reviewRequestDTO.getProductId());

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
            @RequestParam(defaultValue = "5.0") Double maxRating,
            @RequestParam String productId) {
        // Obtener reseñas filtradas por rango y producto
        List<ReviewResponseDTO> reviews = reviewService.getReviewsByRatingRangeAndProduct(minRating, maxRating, productId);
        return ResponseEntity.ok(reviews);
    }

}
