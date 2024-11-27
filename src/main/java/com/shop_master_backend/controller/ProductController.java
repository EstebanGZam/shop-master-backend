package com.shop_master_backend.controller;

import com.shop_master_backend.dto.product.ProductRequestDTO;
import com.shop_master_backend.dto.product.ProductResponseDTO;
import com.shop_master_backend.exception.runtime.SizeNotFoundException;
import com.shop_master_backend.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
	private final ProductServiceImpl productService;

	@PreAuthorize("hasRole('ADMINISTRADOR')")
	@PostMapping
	public ResponseEntity<ProductResponseDTO> addProduct(
			@RequestPart ProductRequestDTO productDTO,
			@RequestPart MultipartFile image) {
		ProductResponseDTO product = productService.addProduct(productDTO, image);
		return ResponseEntity.ok(product);
	}

	@GetMapping
	public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
		List<ProductResponseDTO> products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable String id) {
		ProductResponseDTO product = productService.getProductById(id);
		return ResponseEntity.ok(product);
	}

	@GetMapping("/filter-by-size")
	public ResponseEntity<?> filterProductsBySize(@RequestParam String size) {
		try {
			// Llamar al servicio para obtener los productos filtrados
			List<ProductResponseDTO> products = productService.getProductsBySize(size);

			// Devolver la lista de productos con un estado HTTP 200 (OK)
			return ResponseEntity.ok(products);
		} catch (SizeNotFoundException ex) {
			// Manejar la excepción cuando la talla no existe
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(Map.of("error", ex.getMessage()));
		} catch (Exception ex) {
			// Manejar errores genéricos
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "An unexpected error occurred"));
		}
	}


}
