package com.kappann.stockcontrol.domain.dtos.items.compositions;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemInCompositionRequest {
    @NotNull(message = "Item (component) must be provided to create composition!")
    private Long itemComponentId;

    @NotNull(message = "Required quantity of item in composition must be provided!")
    @PositiveOrZero(message = "Required quantity of item in composition must be positive!")
    private Integer requiredQuantity;
}
