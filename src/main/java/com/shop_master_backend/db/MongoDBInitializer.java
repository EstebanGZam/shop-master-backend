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

			// Crear una imagen para cada producto
			Image jeansImage = Image.builder()
					.id("001")
					.url("https://amigosafety.com/images/productos/1680213793_PANTALON%20FRENTE.png")
					.build();
			imageRepository.save(jeansImage);

			Image shirtImage = Image.builder()
					.id("002")
					.url("https://www.bestsub.com/media/k2/galleries/7009/JA180MV.webp")
					.build();
			imageRepository.save(shirtImage);

			Image sweatshirtImage = Image.builder()
					.id("003")
					.url("https://vansco.vteximg.com.br/arquivos/ids/340280-1000-1000/VN0A7TJPBLK-1.jpg?v=638545982599500000")
					.build();
			imageRepository.save(sweatshirtImage);

			Image jacketImage4 = Image.builder()
					.id("004")
					.url("https://http2.mlstatic.com/D_NQ_NP_679847-CBT77497559641_072024-O.webp")
					.build();
			imageRepository.save(jacketImage4);

			Image jeansImage2 = Image.builder()
					.id("005")
					.url("https://www.muskblog.com/wp-content/uploads/2019/10/PANTS-F50-Holand-Negro-Azul-Marino-Frontal.png")
					.build();
			imageRepository.save(jeansImage2);

			Image shirtImage2 = Image.builder()
					.id("006")
					.url("https://arturocalle.vtexassets.com/arquivos/ids/639609-800-1067?v=638530637019900000&width=800&height=1067&aspect=true")
					.build();
			imageRepository.save(shirtImage2);

			Image sweatshirtImage2 = Image.builder()
					.id("007")
					.url("https://arturocalle.vtexassets.com/arquivos/ids/541482/JOVEN-BUZO-35000668-NEGRO-090_1-1.jpg?v=638168240172930000")
					.build();
			imageRepository.save(sweatshirtImage2);

			Image jacketImage2 = Image.builder()
					.id("008")
					.url("https://www.inkanta.com.co/media/catalog/product/cache/498610b488c09ccbda865aca21aa64dd/5/9/59453_1.jpg")
					.build();
			imageRepository.save(jacketImage2);

			Image chinosImage = Image.builder()
					.id("009")
					.url("https://arturocalle.vtexassets.com/arquivos/ids/620414/HOMBRE-PANTALON-10103688-AZUL-780_1.jpg?v=638455929968070000")
					.build();
			imageRepository.save(chinosImage);

			Image longSleeveShirtImage = Image.builder()
					.id("010")
					.url("https://ferrefarbef.com/wp-content/uploads/2023/01/camisa-oxford-500x500-agrofarbef-1.jpg")
					.build();
			imageRepository.save(longSleeveShirtImage);

			Image sweatshirtImage3 = Image.builder()
					.id("011")
					.url("https://lecoqsportif.com.co/cdn/shop/files/2220603_1_c3ff76d4-04ee-4657-a5f5-24c3060ffbd2.jpg?v=1716504357")
					.build();
			imageRepository.save(sweatshirtImage3);

			Image leatherJacketImage = Image.builder()
					.id("012")
					.url("https://arturocalle.vtexassets.com/arquivos/ids/614008/HOMBRE-CHAQUETA-10132401-NEGRO-090_1.jpg?v=638436172671100000")
					.build();
			imageRepository.save(leatherJacketImage);

			Image sportsSweatshirtImage = Image.builder()
					.id("013")
					.url("https://http2.mlstatic.com/D_NQ_NP_894942-MCO75214001864_032024-O.webp")
					.build();
			imageRepository.save(sportsSweatshirtImage);

			Image flannelShirtImage = Image.builder()
					.id("014")
					.url("https://http2.mlstatic.com/D_NQ_NP_656474-CBT51254571899_082022-O.webp")
					.build();
			imageRepository.save(flannelShirtImage);

			// Crear productos
			Product pantsProduct = Product.builder()
					.id("001")
					.name("Jeans de mezclilla")
					.description("Jeans ajustados de mezclilla azul oscuro")
					.price(49.99)
					.stockQuantity(30)
					.size(sizeMedium)
					.category(pantsCategory)
					.image(jeansImage)
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
					.image(shirtImage)
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
					.image(sweatshirtImage)
					.build();
			productRepository.save(sweatshirtProduct);

			Product jacketProduct4 = Product.builder()
					.id("004")
					.name("Chaqueta ligera para el verano")
					.description("Chaqueta ligera ideal para el clima cálido con un diseño moderno.")
					.price(49.99)
					.stockQuantity(25)
					.size(sizeMedium)
					.category(jacketCategory)
					.image(jacketImage4)
					.build();

			productRepository.save(jacketProduct4);

			Product pantsProduct2 = Product.builder()
					.id("005")
					.name("Pantalones deportivos")
					.description("Pantalones cómodos para hacer ejercicio")
					.price(39.99)
					.stockQuantity(15)
					.size(sizeLarge)
					.category(pantsCategory)
					.image(jeansImage2)
					.build();
			productRepository.save(pantsProduct2);

			Product shirtProduct2 = Product.builder()
					.id("006")
					.name("Camisa casual")
					.description("Camisa de manga corta para el verano")
					.price(19.99)
					.stockQuantity(60)
					.size(sizeXLarge)
					.category(shirtCategory)
					.image(shirtImage2)
					.build();
			productRepository.save(shirtProduct2);

			Product sweatshirtProduct2 = Product.builder()
					.id("007")
					.name("Buzo con capucha")
					.description("Buzo con capucha para climas frescos")
					.price(69.99)
					.stockQuantity(20)
					.size(sizeSmall)
					.category(sweatshirtCategory)
					.image(sweatshirtImage2)
					.build();
			productRepository.save(sweatshirtProduct2);

			Product jacketProduct2 = Product.builder()
					.id("008")
					.name("Chaqueta impermeable")
					.description("Chaqueta de lluvia con capucha y ajuste")
					.price(89.99)
					.stockQuantity(18)
					.size(sizeMedium)
					.category(jacketCategory)
					.image(jacketImage2)
					.build();
			productRepository.save(jacketProduct2);

			Product pantsProduct3 = Product.builder()
					.id("009")
					.name("Pantalón chino")
					.description("Pantalón chino de corte recto y color beige")
					.price(45.99)
					.stockQuantity(25)
					.size(sizeMedium)
					.category(pantsCategory)
					.image(chinosImage)
					.build();
			productRepository.save(pantsProduct3);

			Product shirtProduct3 = Product.builder()
					.id("010")
					.name("Camiseta de manga larga")
					.description("Camiseta de manga larga en colores neutros")
					.price(24.99)
					.stockQuantity(50)
					.size(sizeLarge)
					.category(shirtCategory)
					.image(longSleeveShirtImage)
					.build();
			productRepository.save(shirtProduct3);

			Product sweatshirtProduct3 = Product.builder()
					.id("011")
					.name("Buzo con logo")
					.description("Buzo con el logo en el pecho, ideal para el frío")
					.price(79.99)
					.stockQuantity(30)
					.size(sizeXLarge)
					.category(sweatshirtCategory)
					.image(sweatshirtImage3)
					.build();
			productRepository.save(sweatshirtProduct3);

			Product jacketProduct3 = Product.builder()
					.id("012")
					.name("Chaqueta de cuero")
					.description("Chaqueta de cuero sintético, elegante y cómoda")
					.price(120.00)
					.stockQuantity(10)
					.size(sizeLarge)
					.category(jacketCategory)
					.image(leatherJacketImage)
					.build();
			productRepository.save(jacketProduct3);

			Product sweatshirtProduct4 = Product.builder()
					.id("013")
					.name("Buzo de deporte")
					.description("Buzo de deporte con cierre frontal y capucha")
					.price(65.99)
					.stockQuantity(40)
					.size(sizeMedium)
					.category(sweatshirtCategory)
					.image(sportsSweatshirtImage)
					.build();
			productRepository.save(sweatshirtProduct4);

			Product shirtProduct4 = Product.builder()
					.id("014")
					.name("Camisa de manga larga de franela")
					.description("Camisa de franela de manga larga, perfecta para el invierno")
					.price(49.99)
					.stockQuantity(35)
					.size(sizeMedium)
					.category(shirtCategory)
					.image(flannelShirtImage)
					.build();
			productRepository.save(shirtProduct4);

			// Crear reseñas
			Review review1 = Review.builder()
					.id("001")
					.productId("005") // Producto relacionado con la primera orden
					.userId(1)
					.rating(4.5)
					.comment("Buena calidad y precio justo.")
					.build();
			reviewRepository.save(review1);

			Review review2 = Review.builder()
					.id("002")
					.productId("002") // Producto relacionado con la segunda orden
					.userId(2)
					.rating(5.0)
					.comment("Excelente diseño y ajuste.")
					.build();
			reviewRepository.save(review2);

			Review review3 = Review.builder()
					.id("003")
					.productId("007") // Producto relacionado con la tercera orden
					.userId(3)
					.rating(4.0)
					.comment("Cumple con las expectativas, aunque algo caro.")
					.build();
			reviewRepository.save(review3);

			Review review4 = Review.builder()
					.id("004")
					.productId("004") // Producto relacionado con la cuarta orden
					.userId(4)
					.rating(3.5)
					.comment("Buen producto, pero podría ser más duradero.")
					.build();
			reviewRepository.save(review4);

			Review review5 = Review.builder()
					.id("005")
					.productId("003") // Producto relacionado con la quinta orden
					.userId(5)
					.rating(5.0)
					.comment("Muy cómodo y abrigado.")
					.build();
			reviewRepository.save(review5);

			Review review6 = Review.builder()
					.id("006")
					.productId("006") // Producto relacionado con la sexta orden
					.userId(2)
					.rating(4.5)
					.comment("Estilo moderno y buen material.")
					.build();
			reviewRepository.save(review6);

			Review review7 = Review.builder()
					.id("007")
					.productId("008") // Producto relacionado con la séptima orden
					.userId(3)
					.rating(4.0)
					.comment("Perfecto para días de lluvia.")
					.build();
			reviewRepository.save(review7);

			Review review8 = Review.builder()
					.id("008")
					.productId("009") // Producto relacionado con la octava orden
					.userId(2)
					.rating(5.0)
					.comment("Muy cómodo para uso diario.")
					.build();
			reviewRepository.save(review8);

			Review review9 = Review.builder()
					.id("009")
					.productId("010") // Producto relacionado con la novena orden
					.userId(4)
					.rating(4.0)
					.comment("Buena calidad y diseño clásico.")
					.build();
			reviewRepository.save(review9);

			Review review10 = Review.builder()
					.id("010")
					.productId("011") // Producto relacionado con la décima orden
					.userId(5)
					.rating(5.0)
					.comment("Material de excelente calidad.")
					.build();
			reviewRepository.save(review10);

			Review review11 = Review.builder()
					.id("011")
					.productId("012") // Producto relacionado con la undécima orden
					.userId(6)
					.rating(4.5)
					.comment("Muy elegante, perfecto para ocasiones especiales.")
					.build();
			reviewRepository.save(review11);
		};
	}

}
