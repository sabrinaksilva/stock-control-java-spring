package com.kappann.stockcontrol.fixtures;

import com.kappann.stockcontrol.domain.dtos.items.componentItems.ComponentRequest;
import com.kappann.stockcontrol.utils.ConstantsTestsUtils;

import java.math.BigDecimal;

public class ComponentsTestsFixtures {


    public static ComponentRequest buildComponentRequestDTO (BigDecimal costPrice, BigDecimal profitValue) {
        return ComponentRequest.builder()
                .name(ConstantsTestsUtils.DEFAULT_NAMES.get(0))
                .description(ConstantsTestsUtils.DEFAULT_DESCRIPTIONS.get(0))
                .costPrice(costPrice)
                .profitValue(profitValue)
                .build();
    }

}
