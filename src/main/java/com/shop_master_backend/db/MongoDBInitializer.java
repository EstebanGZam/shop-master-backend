package com.shop_master_backend.db;

import com.shop_master_backend.entity.mongodb.*;
import com.shop_master_backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MongoDBInitializer {

	private final CategoryRepository categoryRepository;
	private final ImageRepository imageRepository;
	private final ProductRepository productRepository;
	private final ReviewRepository reviewRepository;
	private final SizeRepository sizeRepository;

	@Bean
	CommandLineRunner initDatabase() {
		return args -> {
			categoryRepository.deleteAll();
			imageRepository.deleteAll();
			productRepository.deleteAll();
			reviewRepository.deleteAll();
			sizeRepository.deleteAll();

			// Crear categorías
			Category shirtCategory = Category.builder()
					.id("001")
					.name("Camisetas")
					.description("Ropa formal y casual para hombres y mujeres")
					.build();
			categoryRepository.save(shirtCategory);

			Category pantsCategory = Category.builder()
					.id("002")
					.name("Pantalones")
					.description("Pantalones de mezclilla, chinos y más")
					.build();
			categoryRepository.save(pantsCategory);

			Category sweatshirtCategory = Category.builder()
					.id("003")
					.name("Buzos")
					.description("Buzos cómodos para clima frío")
					.build();
			categoryRepository.save(sweatshirtCategory);

			Category jacketCategory = Category.builder()
					.id("004")
					.name("Chaquetas")
					.description("Chaquetas para clima frío y de moda")
					.build();
			categoryRepository.save(jacketCategory);

			// Crear tamaños
			Size sizeSmall = Size.builder()
					.id("001")
					.name("S")
					.build();
			sizeRepository.save(sizeSmall);

			Size sizeMedium = Size.builder()
					.id("002")
					.name("M")
					.build();
			sizeRepository.save(sizeMedium);

			Size sizeLarge = Size.builder()
					.id("003")
					.name("L")
					.build();
			sizeRepository.save(sizeLarge);

			Size sizeXLarge = Size.builder()
					.id("004")
					.name("XL")
					.build();
			sizeRepository.save(sizeXLarge);

			// Crear una imagen de ejemplo
			Image image = Image.builder()
					.id("001")
					.url("https://res.cloudinary.com/dwlrgpkgz/image/upload/v1/products/9GeRjyvDkLLvCH6S23rR")
					.build();
			imageRepository.save(image);

			// Crear productos
			Product pantsProduct = Product.builder()
					.id("001")
					.name("Jeans de mezclilla")
					.description("Jeans ajustados de mezclilla azul oscuro")
					.price(49.99)
					.stockQuantity(30)
					.size(sizeMedium)
					.category(pantsCategory)
					.image(image)
					.build();
			productRepository.save(pantsProduct);

			Product shirtProduct = Product.builder()
					.id("002")
					.name("Camisa de algodón")
					.description("Camisa de algodón 100% para uso diario")
					.price(29.99)
					.stockQuantity(50)
					.size(sizeSmall)
					.category(shirtCategory)
					.image(image)
					.build();
			productRepository.save(shirtProduct);

			Product sweatshirtProduct = Product.builder()
					.id("003")
					.name("Buzo Pullover")
					.description("Buzo pullover negro para climas fríos")
					.price(59.99)
					.stockQuantity(40)
					.size(sizeMedium)
					.category(sweatshirtCategory)
					.image(image)
					.build();
			productRepository.save(sweatshirtProduct);

			// Crear reseñas
			Review pantsReview = Review.builder()
					.id("001")
					.productId(pantsProduct.getId())
					.userId(1)
					.rating(5.0)
					.comment("Excelente ajuste y material resistente.")
					.build();
			reviewRepository.save(pantsReview);

			Review shirtReview = Review.builder()
					.id("002")
					.productId(shirtProduct.getId())
					.userId(2)
					.rating(4.0)
					.comment("Muy cómoda y de buena calidad.")
					.build();
			reviewRepository.save(shirtReview);

			Review sweatshirtReview = Review.builder()
					.id("003")
					.productId(sweatshirtProduct.getId())
					.userId(3)
					.rating(4.0)
					.comment("Perfecto para el invierno, muy abrigado.")
					.build();
			reviewRepository.save(sweatshirtReview);
		};
	}

}
