package com.shop_master_backend.repository;

import com.shop_master_backend.entity.sql.ShoppingCart;
import com.shop_master_backend.entity.sql.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    Optional<ShoppingCart> findByUser(User user);

}
