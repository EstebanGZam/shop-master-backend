package com.shop_master_backend.repository;

import com.shop_master_backend.entity.sql.PaymentDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Integer> {}
