package com.kappann.stockcontrol.utils.prices;

import com.kappann.stockcontrol.domain.models.products.Product;
import com.kappann.stockcontrol.domain.models.products.ProductComponent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PricesCalculatorUtils {

  public static BigDecimal getCostPriceFromComponents(List<ProductComponent> components) {
    if (components == null) {
      components = new ArrayList<>();
    }
    return components
        .stream()
        .map(productComponent -> {
          Product product = productComponent.getComponentProduct();
          return isComponentNullOrMissingValues(productComponent, product) ? BigDecimal.ZERO
              : multiplyUnitaryCostByQuantity(productComponent, product);
        })
        .reduce(BigDecimal::add)
        .orElse(BigDecimal.ZERO);
  }

  private static BigDecimal multiplyUnitaryCostByQuantity(ProductComponent productComponent,
      Product product) {
    return product.getCostPrice().multiply(BigDecimal.valueOf(productComponent.getQuantity()));
  }

  private static boolean isComponentNullOrMissingValues(ProductComponent productComponent,
      Product product) {
    return product == null || product.getCostPrice() == null
        || productComponent.getQuantity() == null;
  }

}
