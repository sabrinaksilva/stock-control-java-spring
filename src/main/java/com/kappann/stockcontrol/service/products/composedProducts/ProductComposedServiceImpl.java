package com.kappann.stockcontrol.service.products.composedProducts;

import com.kappann.stockcontrol.domain.dtos.products.compositions.ProductComposedRequest;
import com.kappann.stockcontrol.domain.models.products.Product;
import com.kappann.stockcontrol.domain.models.products.ProductComponent;
import com.kappann.stockcontrol.mapper.ComponentProductMapper;
import com.kappann.stockcontrol.repository.products.ProductRepository;
import com.kappann.stockcontrol.utils.prices.PricesCalculatorUtils;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ProductComposedServiceImpl implements ProductComposedService {

  private final ProductRepository productRepository;

  @Override
  public Long saveComposedProduct(ProductComposedRequest productComposedRequest) {
    Set<ProductComponent> componentsEntities = new HashSet<>();
    addComponents(productComposedRequest, componentsEntities);
    BigDecimal costPrice = PricesCalculatorUtils.getCostPriceFromComponents(componentsEntities);

    Product composedProduct = Product.builder()
        .name(productComposedRequest.getName())
        .description(productComposedRequest.getDescription())
        .components(componentsEntities)
        .costPrice(costPrice)
        .sellingPrice(costPrice.add(productComposedRequest.getProfitValue()))
        .build();

    return productRepository.save(composedProduct).getId();
  }

  @Override
  public Integer findAmountPossibleToProduceFromComponentsInStock(Product parentProduct,
      Set<ProductComponent> components) {

    Map<ProductComponent, Integer> amountsToProduce = HashMap.newHashMap(components.size());

    components.forEach(component ->
        amountsToProduce.put(component, component.getComponentProduct().getCurrentQuantityInStock()
            / component.getRequiredQuantity())
    );

    return amountsToProduce.values().stream().min(Comparator.naturalOrder()).orElse(0);
  }


  private void addComponents(ProductComposedRequest productComposedRequest,
      Set<ProductComponent> components) {
    productComposedRequest.getComponents().forEach(component -> {
      Product productComponent = productRepository
          .findById(component.getComponentId())
          .orElseThrow(() -> new EntityNotFoundException(
              "Component product not found! Id = " + component.getComponentId()));
      components.add(ComponentProductMapper.toProductComponentEntity(productComponent,
          component.getRequiredQuantity()));
    });
  }
}