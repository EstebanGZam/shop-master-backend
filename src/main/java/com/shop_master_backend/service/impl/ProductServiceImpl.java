package com.shop_master_backend.service.impl;

import com.shop_master_backend.dto.product.ProductRequestDTO;
import com.shop_master_backend.dto.product.ProductResponseDTO;
import com.shop_master_backend.entity.mongodb.Category;
import com.shop_master_backend.entity.mongodb.Image;
import com.shop_master_backend.entity.mongodb.Product;
import com.shop_master_backend.entity.mongodb.Size;
import com.shop_master_backend.exception.runtime.CategoryNotFoundException;
import com.shop_master_backend.exception.runtime.ProductNotFoundException;
import com.shop_master_backend.exception.runtime.SizeNotFoundException;
import com.shop_master_backend.mapper.product.ProductMapper;
import com.shop_master_backend.repository.CategoryRepository;
import com.shop_master_backend.repository.ImageRepository;
import com.shop_master_backend.repository.ProductRepository;
import com.shop_master_backend.repository.SizeRepository;
import com.shop_master_backend.service.interfaces.CloudinaryService;
import com.shop_master_backend.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final CloudinaryService cloudinaryService;
	private final CategoryRepository categoryRepository;
	private final ImageRepository imageRepository;
	private final ProductRepository productRepository;
	private final SizeRepository sizeRepository;
	private final ProductMapper productMapper;

	@Override
	public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO, MultipartFile image) {
		// Obtener el Size y Category y lanzar excepción si no existen
		Size size = sizeRepository.findById(productRequestDTO.getSizeId())
				.orElseThrow(() -> new SizeNotFoundException("Size not found"));
		Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
				.orElseThrow(() -> new CategoryNotFoundException("Category not found"));

		// Subir la imagen a Cloudinary
		String imageUrl = cloudinaryService.uploadFile(image, "products");

		// Crear y guardar la imagen asociada al producto
		Image productImage = Image.builder()
				.url(imageUrl)
				.build();
		imageRepository.save(productImage);

		// Mapear el DTO a la entidad Product
		Product product = productMapper.toProduct(productRequestDTO);

		// Asignar las entidades obtenidas (size, category e imagen) al producto
		product.setSize(size);
		product.setCategory(category);
		product.setImage(productImage);

		// Guardar el producto en la base de datos
		product = productRepository.save(product);

		return productMapper.toProductResponseDTO(product);
	}

	@Override
	public List<ProductResponseDTO> getAllProducts() {
		return productRepository.findAll().stream()
				.map(productMapper::toProductResponseDTO)
				.collect(Collectors.toList());
	}

	@Override
	public ProductResponseDTO getProductById(String id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found"));
		return productMapper.toProductResponseDTO(product);
	}

	@Override
	public List<ProductResponseDTO> getProductsBySize(String sizeId) {
		Size size = sizeRepository.findById(sizeId)
				.orElseThrow(() -> new SizeNotFoundException("Size not found"));
		List<Product> products = productRepository.findBySizeAndStockQuantityGreaterThan(size, 0);
		return products.stream()
				.map(productMapper::toProductResponseDTO)
				.toList();
	}

}
