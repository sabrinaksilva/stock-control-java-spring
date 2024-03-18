package com.kappann.stockcontrol.repository.products;

import com.kappann.stockcontrol.domain.models.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
