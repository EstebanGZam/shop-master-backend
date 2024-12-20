package com.shop_master_backend.repository;

import com.shop_master_backend.entity.sql.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

	List<OrderDetail> findByOrderId(Integer orderId);

	// Buscar si el usuario ha comprado un producto
	List<OrderDetail> findByOrderUserIdAndProduct(Integer userId, String productId);

}
