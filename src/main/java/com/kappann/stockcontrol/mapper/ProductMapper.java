package com.kappann.stockcontrol.mapper;

import com.kappann.stockcontrol.domain.dtos.products.components.ComponentOfProductRequest;
import com.kappann.stockcontrol.domain.dtos.products.compositions.ProductComposedRequest;
import com.kappann.stockcontrol.domain.models.products.Product;
import com.kappann.stockcontrol.domain.models.products.ProductComponent;
import com.kappann.stockcontrol.utils.prices.PricesCalculatorUtils;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMapper {

  public static Product toEntity(ComponentOfProductRequest request) {
    return Product
        .builder()
        .name(request.getName())
        .description(request.getDescription())
        .components(new ArrayList<>())
        .costPrice(request.getCostPrice())
        .sellingPrice(request.getCostPrice().add(request.getProfitValue()))
        .build();
  }

  public static Product toEntity(ProductComposedRequest request,
      List<ProductComponent> components) {
    return Product
        .builder()
        .name(request.getName())
        .description(request.getDescription())
        .components(components)
        .costPrice(PricesCalculatorUtils.getCostPriceFromComponents(components))
        .build();
  }
}
