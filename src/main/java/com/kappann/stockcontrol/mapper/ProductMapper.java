package com.kappann.stockcontrol.mapper;

import com.kappann.stockcontrol.domain.dtos.products.components.ComponentOfProductRequest;
import com.kappann.stockcontrol.domain.dtos.products.compositions.ProductComposedRequest;
import com.kappann.stockcontrol.domain.models.products.Product;
import com.kappann.stockcontrol.domain.models.products.ProductComponent;
import com.kappann.stockcontrol.utils.prices.PricesCalculatorUtils;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMapper {

  public static Product toEntity(ComponentOfProductRequest request) {
    return Product
        .builder()
        .name(request.getName())
        .description(request.getDescription())
        .components(new HashSet<>())
        .costPrice(request.getCostPrice())
        .sellingPrice(request.getCostPrice().add(request.getProfitValue()))
        .build();
  }

  public static Product toEntity(ProductComposedRequest request,
      Set<ProductComponent> components) {
    return Product
        .builder()
        .name(request.getName())
        .description(request.getDescription())
        .components(components)
        .costPrice(PricesCalculatorUtils.getCostPriceFromComponents(components))
        .build();
  }
}
