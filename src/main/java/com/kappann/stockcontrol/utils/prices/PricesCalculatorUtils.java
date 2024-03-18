package com.kappann.stockcontrol.utils.prices;

import com.kappann.stockcontrol.domain.models.products.Product;
import com.kappann.stockcontrol.domain.models.products.ProductComponent;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class PricesCalculatorUtils {

  public static BigDecimal getCostPriceFromComponents(Set<ProductComponent> components) {
    if (components == null) {
      components = new HashSet<>();
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
    return product.getCostPrice()
        .multiply(BigDecimal.valueOf(productComponent.getRequiredQuantity()));
  }

  private static boolean isComponentNullOrMissingValues(ProductComponent productComponent,
      Product product) {
    return product == null || product.getCostPrice() == null
        || productComponent.getRequiredQuantity() == null;
  }

}
