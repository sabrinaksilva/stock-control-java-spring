package com.kappann.stockcontrol.service.products.components;

import com.kappann.stockcontrol.domain.dtos.products.components.ComponentOfProductRequest;
import com.kappann.stockcontrol.domain.models.products.Product;
import com.kappann.stockcontrol.domain.models.products.ProductComponent;
import jakarta.validation.Valid;
import java.util.Set;

public interface ProductComponentService {

  Long saveComponent(@Valid ComponentOfProductRequest componentOfProductRequest);

  Set<ProductComponent> findAllComponentsByParentProduct(Product parentProduct);

  Set<ProductComponent> getComponentsOfParentProduct(Product parentProduct);
}
