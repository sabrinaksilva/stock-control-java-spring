package com.kappann.stockcontrol.repository.products;

import com.kappann.stockcontrol.domain.models.products.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query(value = "FROM Product p "
      + " LEFT JOIN FETCH p.components components "
      + " WHERE p.id = :productId", nativeQuery = false)
  Optional<Product> findByIdFetchComponents(@Param("productId") Long productId);
}
