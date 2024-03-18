package com.kappann.stockcontrol.domain.dtos.products.compositions;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
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
  private Set<ProductComponentRequest> components = new HashSet<>();

  @JsonSetter(nulls = Nulls.SKIP)
  @PositiveOrZero(message = "The current quantity available/ready in stock must be >= 0")
  private Integer currentQuantityInStock = 0;

}
