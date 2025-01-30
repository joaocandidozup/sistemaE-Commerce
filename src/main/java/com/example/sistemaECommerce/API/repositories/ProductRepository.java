package com.example.sistemaECommerce.API.repositories;

import com.example.sistemaECommerce.API.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity findByNameIgnoreCase(String name);
}
