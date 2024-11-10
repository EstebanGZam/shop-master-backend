package com.shop_master_backend.entity.mongodb;

import org.springframework.data.annotation.Id;
import lombok.Data;

@Data
public class Image {
    @Id
    private String id;
    private byte[] content;

    public Image() {}

    public Image(String id, byte[] content) {
        this.id = id;
        this.content = content;
    }
}