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

    public Product() {}

    public Product(String id, String name, String description, BigDecimal price, Integer stockQuantity, LocalDate creationDate, Size size, Category category, Image image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.creationDate = creationDate;
        this.size = size;
        this.category = category;
        this.image = image;
    }
}