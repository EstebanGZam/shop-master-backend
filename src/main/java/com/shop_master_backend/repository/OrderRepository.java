package com.shop_master_backend.repository;

import com.shop_master_backend.entity.sql.Order;
import com.shop_master_backend.entity.sql.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser(User user);
}