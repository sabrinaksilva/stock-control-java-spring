package com.kappann.stockcontrol.service.products.components;

import com.kappann.stockcontrol.domain.dtos.products.components.ComponentOfProductRequest;
import com.kappann.stockcontrol.domain.models.products.Product;
import com.kappann.stockcontrol.mapper.ProductMapper;
import com.kappann.stockcontrol.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ProductComponentServiceImpl implements ProductComponentService {
    private final ProductRepository productRepository;

    @Override
    public Long saveComponent (ComponentOfProductRequest componentOfProductRequest) {
        Product product = ProductMapper.toEntity(componentOfProductRequest);
        return (productRepository.save(product)).getId();
    }
}
