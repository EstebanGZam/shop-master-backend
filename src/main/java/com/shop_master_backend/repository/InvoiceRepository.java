package com.shop_master_backend.repository;

import com.shop_master_backend.entity.sql.Invoice;
import com.shop_master_backend.entity.sql.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    List<Invoice> findByOrderUser(User user);
}