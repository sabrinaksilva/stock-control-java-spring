package com.kappann.stockcontrol.service.products.components;

import com.kappann.stockcontrol.domain.dtos.products.components.ComponentOfProductRequest;
import jakarta.validation.Valid;

public interface ProductComponentService {
    Long saveComponent (@Valid ComponentOfProductRequest componentOfProductRequest);
}
