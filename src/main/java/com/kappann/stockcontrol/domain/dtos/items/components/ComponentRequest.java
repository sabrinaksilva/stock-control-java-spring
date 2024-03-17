package com.kappann.stockcontrol.domain.dtos.items.components;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComponentRequest {
    @NotEmpty(message = "Name is required!")
    private String name;
    private String description;
    @NotNull(message = "Cost price is required!")
    @PositiveOrZero(message = "Cost price must be positive!")
    private BigDecimal costPrice;
    @NotNull(message = "Profit value is required!")
    @PositiveOrZero(message = "Profit value must be positive!")
    private BigDecimal profitValue;
}
