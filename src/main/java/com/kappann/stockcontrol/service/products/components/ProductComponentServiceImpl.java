package com.kappann.stockcontrol.service.products.components;

import com.kappann.stockcontrol.domain.dtos.products.components.ComponentOfProductRequest;
import com.kappann.stockcontrol.domain.models.products.Product;
import com.kappann.stockcontrol.domain.models.products.ProductComponent;
import com.kappann.stockcontrol.mapper.ProductMapper;
import com.kappann.stockcontrol.repository.products.ProductComponentRepository;
import com.kappann.stockcontrol.repository.products.ProductRepository;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductComponentServiceImpl implements ProductComponentService {

  private final ProductRepository productRepository;
  private final ProductComponentRepository productComponentRepository;

  @Override
  public Long saveComponent(ComponentOfProductRequest componentOfProductRequest) {
    Product product = ProductMapper.toEntity(componentOfProductRequest);
    return (productRepository.save(product)).getId();
  }

  @Override
  public Set<ProductComponent> findAllComponentsByParentProduct(Product parentProduct) {
    return productComponentRepository.findAllByParentProduct(parentProduct);
  }

  @Override
  public Set<ProductComponent> getComponentsOfParentProduct(Product parentProduct) {
    Set<ProductComponent> components = new HashSet<>();
    if (parentProduct.isComposition()) {
      components = findAllComponentsByParentProduct(
          parentProduct);
    }
    return components;
  }
}
