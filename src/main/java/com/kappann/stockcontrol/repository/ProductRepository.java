package com.kappann.stockcontrol.repository;

import com.kappann.stockcontrol.domain.models.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
