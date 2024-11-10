package com.shop_master_backend.entity.mongodb;

import org.springframework.data.annotation.Id;
import lombok.Data;

@Data
public class Category {
    @Id
    private String id;
    private String name;
    private String description;

    public Category() {}

    public Category(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}