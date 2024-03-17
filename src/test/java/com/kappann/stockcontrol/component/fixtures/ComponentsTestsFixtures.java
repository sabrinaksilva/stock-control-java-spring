package com.kappann.stockcontrol.component.fixtures;

import com.kappann.stockcontrol.domain.dtos.items.components.ComponentRequest;

import java.math.BigDecimal;
import java.util.List;

public class ComponentsTestsFixtures {
    public static final List<String> DEFAULT_NAMES = List.of("Component test name 1", "Component test name 2", "Component test name 3");
    public static final List<String> DEFAULT_DESCRIPTIONS = List.of("Component description 1", "Component description 2", "Component description 3");

    public static ComponentRequest buildComponentRequestDTO (BigDecimal costPrice, BigDecimal profitValue) {
        return ComponentRequest.builder()
                .name(DEFAULT_NAMES.get(0))
                .description(DEFAULT_DESCRIPTIONS.get(0))
                .costPrice(costPrice)
                .profitValue(profitValue)
                .build();
    }

}
