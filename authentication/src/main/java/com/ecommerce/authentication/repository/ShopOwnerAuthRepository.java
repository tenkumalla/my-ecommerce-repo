package com.ecommerce.authentication.repository;

import com.ecommerce.authentication.entity.ShopOwnerAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopOwnerAuthRepository extends JpaRepository<ShopOwnerAuth, Long> {
    Optional<ShopOwnerAuth> findByEmail(String email);
}
