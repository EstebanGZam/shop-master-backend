package com.shop_master_backend.mapper.review;

import com.shop_master_backend.dto.review.ReviewRequestDTO;
import com.shop_master_backend.dto.review.ReviewResponseDTO;
import com.shop_master_backend.entity.mongodb.Review;
import com.shop_master_backend.entity.sql.User;
import com.shop_master_backend.exception.runtime.UserNotFoundException;
import com.shop_master_backend.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ReviewMapper {

    @Autowired
    private UserRepository userRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "userId", ignore = true)
    public abstract Review toReview(ReviewRequestDTO reviewRequestDTO);

    @Mapping(target = "username", expression = "java(getUsername(review.getUserId()))")
    public abstract ReviewResponseDTO toReviewResponseDTO(Review review);

    // Método auxiliar para obtener el nombre del usuario de la base de datos relacional
    protected String getUsername(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return user.getUsername();
    }

}
