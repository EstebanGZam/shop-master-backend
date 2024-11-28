package com.shop_master_backend.controller;

import com.shop_master_backend.constant.NotificationTopics;
import com.shop_master_backend.dto.product.ProductRequestDTO;
import com.shop_master_backend.dto.product.ProductResponseDTO;
import com.shop_master_backend.event.EntityUpdateEvent;
import com.shop_master_backend.exception.runtime.SizeNotFoundException;
import com.shop_master_backend.service.impl.ProductServiceImpl;
import com.shop_master_backend.service.interfaces.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;
    private final NotificationService notificationService;
    private final ApplicationEventPublisher eventPublisher;

    @GetMapping(path = "/stream")
    public SseEmitter streamProducts() {
        SseEmitter emitter = notificationService.subscribe(NotificationTopics.PRODUCTS);

        try {
            List<ProductResponseDTO> products = productService.getAllProducts();
            emitter.send(SseEmitter.event()
                    .name(NotificationTopics.PRODUCTS)
                    .data(products));
        } catch (IOException e) {
            emitter.completeWithError(e);
        }

        return emitter;
    }

    private void notifyProductUpdate() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        eventPublisher.publishEvent(new EntityUpdateEvent<>(NotificationTopics.PRODUCTS, products));
    }

	@PreAuthorize("hasRole('ADMINISTRADOR')")
	@PostMapping
	public ResponseEntity<ProductResponseDTO> addProduct(
			@RequestPart ProductRequestDTO productDTO,
			@RequestPart MultipartFile image) {
		ProductResponseDTO product = productService.addProduct(productDTO, image);

        // Notifica cualquier actualización en los productos a los clientes
        notifyProductUpdate();

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

	@GetMapping("/filter-by-category")
	public ResponseEntity<List<ProductResponseDTO>> filterProductsByCategory(@RequestParam String categoryId) {
		List<ProductResponseDTO> products = productService.getProductsByCategory(categoryId);
		return ResponseEntity.ok(products);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<ProductResponseDTO>> filterProducts(
			@RequestParam(required = false) String size,
			@RequestParam(required = false) String categoryId) {
		List<ProductResponseDTO> products = productService.filterProducts(size, categoryId);
		return ResponseEntity.ok(products);
	}

}
