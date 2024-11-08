package com.shop_master_backend.entity.mongodb;

import org.springframework.data.annotation.Id;
import lombok.Data;

@Data
public class Size {
    @Id
    private String id;
    private String name;
}