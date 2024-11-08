package com.shop_master_backend.config;

import com.shop_master_backend.entity.mongodb.*;
import com.shop_master_backend.repository.ProductRepository;
import com.shop_master_backend.repository.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class MongoDBInitializer {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            productRepository.deleteAll();
            reviewRepository.deleteAll();

            Category shirtCategory = new Category();
            shirtCategory.setId("001");
            shirtCategory.setName("Camisas");
            shirtCategory.setDescription("Ropa formal y casual para hombres y mujeres");

            Category pantsCategory = new Category();
            pantsCategory.setId("002");
            pantsCategory.setName("Pantalones");
            pantsCategory.setDescription("Pantalones de mezclilla, chinos y más");

            Size sizeSmall = new Size();
            sizeSmall.setId("001");
            sizeSmall.setName("S");

            Size sizeMedium = new Size();
            sizeMedium.setId("002");
            sizeMedium.setName("M");

            Image sampleImage = new Image();
            sampleImage.setId("001");
            sampleImage.setContent(new byte[]{});

            Product shirtProduct = new Product();
            shirtProduct.setId("001");
            shirtProduct.setName("Camisa de algodón");
            shirtProduct.setDescription("Camisa de algodón 100% para uso diario");
            shirtProduct.setPrice(BigDecimal.valueOf(29.99));
            shirtProduct.setStockQuantity(50);
            shirtProduct.setCreationDate(LocalDate.now());
            shirtProduct.setSize(sizeSmall);
            shirtProduct.setCategory(shirtCategory);
            shirtProduct.setImage(sampleImage);

            productRepository.save(shirtProduct);

            Product pantsProduct = new Product();
            pantsProduct.setId("002");
            pantsProduct.setName("Jeans de mezclilla");
            pantsProduct.setDescription("Jeans ajustados de mezclilla azul oscuro");
            pantsProduct.setPrice(BigDecimal.valueOf(49.99));
            pantsProduct.setStockQuantity(30);
            pantsProduct.setCreationDate(LocalDate.now());
            pantsProduct.setSize(sizeMedium);
            pantsProduct.setCategory(pantsCategory);
            pantsProduct.setImage(sampleImage);

            productRepository.save(pantsProduct);

            Review shirtReview = new Review();
            shirtReview.setId("001");
            shirtReview.setProductId(shirtProduct.getId());
            shirtReview.setUserId(1);
            shirtReview.setRating(4);
            shirtReview.setComment("Muy cómoda y de buena calidad.");
            shirtReview.setDate(LocalDate.now());

            reviewRepository.save(shirtReview);

            Review pantsReview = new Review();
            pantsReview.setId("002");
            pantsReview.setProductId(pantsProduct.getId());
            pantsReview.setUserId(2);
            pantsReview.setRating(5);
            pantsReview.setComment("Excelente ajuste y material resistente.");
            pantsReview.setDate(LocalDate.now());

            reviewRepository.save(pantsReview);
        };
    }
}