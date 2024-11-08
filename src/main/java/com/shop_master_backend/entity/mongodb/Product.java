package com.shop_master_backend.entity.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Document(collection = "products")
public class Product {
    @Id
    private String id;

    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private LocalDate creationDate;

    private Size size;
    private Category category;
    private Image image;
}