package com.kappann.stockcontrol.repository.products;

import com.kappann.stockcontrol.domain.models.products.Product;
import com.kappann.stockcontrol.domain.models.products.ProductComponent;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductComponentRepository extends JpaRepository<ProductComponent, Long> {

  Set<ProductComponent> findAllByParentProduct(Product parentProduct);
}
