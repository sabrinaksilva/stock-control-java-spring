package com.kappann.stockcontrol.service.stock;

import com.kappann.stockcontrol.domain.dtos.stock.ProductStockResponse;
import com.kappann.stockcontrol.domain.dtos.stock.StockAvailabilityResponse;
import com.kappann.stockcontrol.domain.models.products.Product;
import com.kappann.stockcontrol.domain.models.products.ProductComponent;
import com.kappann.stockcontrol.repository.products.ProductRepository;
import com.kappann.stockcontrol.service.products.components.ProductComponentService;
import com.kappann.stockcontrol.service.products.composedProducts.ProductComposedService;
import com.kappann.stockcontrol.utils.prices.PricesCalculatorUtils;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockServiceImpl implements StockService {

  private final ProductRepository productRepository;
  private final ProductComponentService componentsService;
  private final ProductComposedService compositionsService;


  @Override
  public StockAvailabilityResponse findQuantityInStockReadyToUseAndAvailabilityToProduce(
      Long productId) {

    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new EntityNotFoundException("Product not found!"));

    Set<ProductComponent> components = componentsService.getComponentsOfParentProduct(product);
    BigDecimal costPrice = getCostPrice(product, components);

    ProductStockResponse productResponse = buildStockProductReportResponse(product, costPrice,
        components);
    Set<ProductStockResponse> componentsResponse = new HashSet<>();

    components.forEach(component -> {
      Product componentProduct = component.getComponentProduct();
      Set<ProductComponent> componentsOfThisComponent = componentsService.getComponentsOfParentProduct(
          componentProduct);
      BigDecimal componentCostPrice = getCostPrice(componentProduct, componentsOfThisComponent);
      componentsResponse.add(buildStockProductReportResponse(componentProduct, componentCostPrice,
          componentsOfThisComponent));
    });

    return StockAvailabilityResponse.builder().product(productResponse)
        .components(componentsResponse).build();

  }

  private ProductStockResponse buildStockProductReportResponse(Product product,
      BigDecimal costPrice,
      Set<ProductComponent> components) {
    return ProductStockResponse.builder()
        .productId(product.getId())
        .isComposition(product.isComposition())
        .productName(product.getName())
        .description(product.getDescription())
        .costPrice(costPrice)
        .sellingPrice(product.getSellingPrice())
        .availableQuantityToProduceFromComponents(
            compositionsService.findAmountPossibleToProduceFromComponentsInStock(product,
                components))
        .currentQuantityReadyInStock(product.getCurrentQuantityInStock())
        .build();
  }

  private BigDecimal getCostPrice(Product parentProduct, Set<ProductComponent> components) {
    return (parentProduct.isComposition()) ? PricesCalculatorUtils.getCostPriceFromComponents(
        components) : parentProduct.getCostPrice();
  }


}
