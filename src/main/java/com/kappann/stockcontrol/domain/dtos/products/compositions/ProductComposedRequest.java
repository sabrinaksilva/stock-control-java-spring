package com.kappann.stockcontrol.domain.dtos.products.compositions;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductComposedRequest {

  @NotEmpty(message = "Name is required!")
  private String name;

  private String description;

  @NotNull(message = "Profit value is required!")
  @PositiveOrZero(message = "Profit value must be positive!")
  private BigDecimal profitValue;

  @NotEmpty(message = "Components of this product composed must be provided!")
  private List<ProductComponentRequest> components = new ArrayList<>();

}
