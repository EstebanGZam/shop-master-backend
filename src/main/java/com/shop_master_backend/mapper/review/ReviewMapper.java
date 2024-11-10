package com.shop_master_backend.mapper.review;

import com.shop_master_backend.dto.review.ReviewRequestDTO;
import com.shop_master_backend.dto.review.ReviewResponseDTO;
import com.shop_master_backend.entity.mongodb.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    Review toReview(ReviewRequestDTO reviewRequestDTO);

    ReviewResponseDTO toReviewResponseDTO(Review review);
}