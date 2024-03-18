package com.kappann.stockcontrol.utils.prices;

import com.kappann.stockcontrol.domain.models.products.ProductComponent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PricesCalculatorUtils {

    public static BigDecimal getCostPriceFromComponents (List<ProductComponent> components) {
        if (components == null) components = new ArrayList<>();
        return components
                .stream()
                .map(component ->
                        component.getComponentProduct().getCostPrice()
                                .multiply(BigDecimal.valueOf(component.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

}
