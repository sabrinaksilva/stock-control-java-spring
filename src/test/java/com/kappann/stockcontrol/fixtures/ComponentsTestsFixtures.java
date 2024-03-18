package com.kappann.stockcontrol.fixtures;

import com.kappann.stockcontrol.domain.dtos.products.components.ComponentOfProductRequest;
import com.kappann.stockcontrol.utils.ConstantsTestsUtils;

import java.math.BigDecimal;

public class ComponentsTestsFixtures {


    public static ComponentOfProductRequest buildComponentRequestDTO (BigDecimal costPrice, BigDecimal profitValue) {
        return ComponentOfProductRequest.builder()
                .name(ConstantsTestsUtils.DEFAULT_COMPONENTS_NAMES.get(0))
                .description(ConstantsTestsUtils.DEFAULT_COMPONENTS_DESCRIPTIONS.get(0))
                .costPrice(costPrice)
                .profitValue(profitValue)
                .build();
    }

}
