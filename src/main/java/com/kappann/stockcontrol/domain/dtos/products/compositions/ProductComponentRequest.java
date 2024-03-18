package com.kappann.stockcontrol.domain.dtos.products.compositions;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductComponentRequest {
    @NotNull(message = "Product (component) must be provided to create composition!")
    private Long componentId;

    @NotNull(message = "Required quantity of such product in composition must be provided!")
    @PositiveOrZero(message = "Required quantity of such product in composition must be positive!")
    private Integer requiredQuantity;
}
