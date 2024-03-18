package com.kappann.stockcontrol.domain.dtos.stock;

import java.util.Set;
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
public class StockAvailabilityResponse {

  private ProductStockResponse product;

  private Set<ProductStockResponse> components;

}
