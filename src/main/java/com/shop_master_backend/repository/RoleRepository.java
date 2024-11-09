package com.shop_master_backend.repository;

import com.shop_master_backend.entity.sql.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findById(Integer id);

}
