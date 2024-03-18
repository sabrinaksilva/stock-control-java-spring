package com.kappann.stockcontrol.fixtures;

import com.kappann.stockcontrol.domain.dtos.products.compositions.ProductComponentRequest;
import com.kappann.stockcontrol.domain.dtos.products.compositions.ProductComposedRequest;
import com.kappann.stockcontrol.utils.ConstantsTestsUtils;
import com.kappann.stockcontrol.utils.NumberTestsUtils;

import java.util.List;

public class ComposedProductsTestsFixtures {
    public static ProductComponentRequest buildProductInCompositionRequest (Long componentId) {
        return ProductComponentRequest.builder()
                .componentId(componentId)
                .requiredQuantity(NumberTestsUtils.generateRandomIntegerPositive())
                .build();
    }

    public static ProductComponentRequest buildProductInCompositionRequest (Long componentId, Integer requiredQuantity) {
        return ProductComponentRequest.builder()
                .componentId(componentId)
                .requiredQuantity(requiredQuantity)
                .build();
    }


    public static ProductComposedRequest buildComposedProductRequestRandomComponentsQuantities (List<Long> componentsIds) {
        List<ProductComponentRequest> componentsOfComposedProduct = componentsIds
                .stream()
                .map(ComposedProductsTestsFixtures::buildProductInCompositionRequest
                )
                .toList();


        return ProductComposedRequest.builder()
                .name(ConstantsTestsUtils.DEFAULT_COMPOSED_ITEMS_NAMES.get(0))
                .description(ConstantsTestsUtils.DEFAULT_COMPOSED_ITEMS_DESCRIPTIONS.get(0))
                .profitValue(NumberTestsUtils.generateRandomBigDecimalPositive())
                .build();
    }

    public static ProductComposedRequest buildComposedProductRequestFromComponents (List<ProductComponentRequest> componentsOfComposedProduct) {
        return ProductComposedRequest.builder()
                .name(ConstantsTestsUtils.DEFAULT_COMPOSED_ITEMS_NAMES.getLast())
                .description(ConstantsTestsUtils.DEFAULT_COMPOSED_ITEMS_DESCRIPTIONS.getLast())
                .profitValue(NumberTestsUtils.generateRandomBigDecimalPositive())
                .components(componentsOfComposedProduct)
                .build();
    }


}
