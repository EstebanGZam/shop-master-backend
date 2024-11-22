package com.shop_master_backend.repository;

import com.shop_master_backend.entity.sql.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {}
