package com.kappann.stockcontrol.service.products.composedProducts;

import com.kappann.stockcontrol.domain.dtos.products.compositions.ProductComposedRequest;
import com.kappann.stockcontrol.domain.models.products.Product;
import com.kappann.stockcontrol.domain.models.products.ProductComponent;
import jakarta.validation.Valid;
import java.util.Set;

public interface ProductComposedService {

  Long saveComposedProduct(@Valid ProductComposedRequest productComposedRequest);

  Integer findAmountPossibleToProduceFromComponentsInStock(Product parentProduct,
      Set<ProductComponent> components);


}
