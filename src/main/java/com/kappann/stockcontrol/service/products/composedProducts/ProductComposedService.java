package com.kappann.stockcontrol.service.products.composedProducts;

import com.kappann.stockcontrol.domain.dtos.products.compositions.ProductComposedRequest;
import jakarta.validation.Valid;

public interface ProductComposedService {

  Long saveComposedProduct(@Valid ProductComposedRequest productComposedRequest);


}
