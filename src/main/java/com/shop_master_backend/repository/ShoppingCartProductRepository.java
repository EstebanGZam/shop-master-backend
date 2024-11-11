package com.shop_master_backend.repository;

import com.shop_master_backend.entity.sql.ShoppingCartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartProductRepository extends JpaRepository<ShoppingCartProduct, Integer> {}