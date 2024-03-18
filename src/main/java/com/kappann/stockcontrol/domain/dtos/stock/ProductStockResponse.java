package com.kappann.stockcontrol.domain.dtos.stock;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductStockResponse {

  private Long productId;

  private Boolean isComposition;

  private String productName;

  private String description;

  private BigDecimal costPrice;

  private BigDecimal sellingPrice;

  private Integer availableQuantityToProduceFromComponents;

  private Integer currentQuantityReadyInStock;

}
