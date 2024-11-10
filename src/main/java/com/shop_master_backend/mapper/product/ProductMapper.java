package com.shop_master_backend.mapper.product;

import com.shop_master_backend.dto.product.ProductRequestDTO;
import com.shop_master_backend.dto.product.ProductResponseDTO;
import com.shop_master_backend.entity.mongodb.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Base64;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(source = "sizeId", target = "size.id")
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "imageId", target = "image.id")
    Product toProduct(ProductRequestDTO productRequestDTO);

    @Mapping(source = "size.name", target = "sizeName")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "image.content", target = "imageUrl", qualifiedByName = "convertImageToUrl")
    ProductResponseDTO toProductResponseDTO(Product product);

    // Método auxiliar para convertir la imagen a URL
    @Named("convertImageToUrl")
    default String convertImageToUrl(byte[] imageContent) {
        return "data:image/png;base64," + Base64.getEncoder().encodeToString(imageContent);
    }

}
