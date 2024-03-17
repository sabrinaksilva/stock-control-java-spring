package com.kappann.stockcontrol.domain.dtos.items.compositions;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompositionRequest {
    @NotEmpty(message = "Name is required!")
    private String name;

    private String description;

    @NotNull(message = "Profit value is required!")
    @PositiveOrZero(message = "Profit value must be positive!")
    private BigDecimal profitValue;

    @NotEmpty(message = "Components of this item must be provided!")
    private List<ItemInCompositionRequest> components = new ArrayList<>();

}
