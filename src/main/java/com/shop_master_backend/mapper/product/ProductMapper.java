package com.shop_master_backend.mapper.product;

import com.shop_master_backend.dto.product.ProductRequestDTO;
import com.shop_master_backend.dto.product.ProductResponseDTO;
import com.shop_master_backend.entity.mongodb.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "size", ignore = true)
    @Mapping(target = "category", ignore = true)
    Product toProduct(ProductRequestDTO productRequestDTO);

    @Mapping(source = "size.name", target = "sizeName")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "image.url", target = "imageUrl")
    ProductResponseDTO toProductResponseDTO(Product product);

}
