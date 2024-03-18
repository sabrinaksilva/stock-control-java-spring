package com.kappann.stockcontrol.composedProducts;

import com.kappann.stockcontrol.domain.models.products.Product;
import com.kappann.stockcontrol.domain.models.products.ProductComponent;
import com.kappann.stockcontrol.mapper.ComponentProductMapper;
import com.kappann.stockcontrol.repository.ProductRepository;
import com.kappann.stockcontrol.service.products.composedProducts.ProductComposedService;
import com.kappann.stockcontrol.utils.ConstantsTestsUtils;
import com.kappann.stockcontrol.utils.prices.PricesCalculatorUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComposedProductsUnitTests {

    @MockBean
    ProductRepository productRepository;

    @Autowired
    ProductComposedService productComposedService;


    @Test
    @DisplayName("Should calculate cost price of composed product by summing the cost prices of each of its components")
    void shouldCalculateCostPriceFromComponents () {
        List<BigDecimal> costPrices = List.of(new BigDecimal("2.99"), new BigDecimal("10"), new BigDecimal("5.01"));
        List<BigDecimal> profitValues = List.of(new BigDecimal("1.5"), new BigDecimal("2.5"), new BigDecimal("20"));
        List<Integer> quantities = List.of(1, 3, 5);
        List<Long> componentsIds = List.of(8L, 13L, 21L);

        BigDecimal expectedTotalCostPrice = new BigDecimal("58.04");


        List<Product> componentsEntity = List.of(
                Product.builder()
                        .id(componentsIds.get(0))
                        .name(ConstantsTestsUtils.DEFAULT_COMPONENTS_NAMES.get(0))
                        .description(ConstantsTestsUtils.DEFAULT_COMPONENTS_DESCRIPTIONS.get(0))
                        .costPrice(costPrices.get(0))
                        .sellingPrice(costPrices.get(0).add(profitValues.get(0)))
                        .build(),

                Product.builder()
                        .id(componentsIds.get(1))
                        .name(ConstantsTestsUtils.DEFAULT_COMPONENTS_NAMES.get(1))
                        .description(ConstantsTestsUtils.DEFAULT_COMPONENTS_DESCRIPTIONS.get(1))
                        .costPrice(costPrices.get(1))
                        .sellingPrice(costPrices.get(1).add(profitValues.get(1)))
                        .build(),

                Product.builder()
                        .id(componentsIds.get(2))
                        .name(ConstantsTestsUtils.DEFAULT_COMPONENTS_NAMES.get(2))
                        .description(ConstantsTestsUtils.DEFAULT_COMPONENTS_DESCRIPTIONS.get(2))
                        .costPrice(costPrices.get(2))
                        .sellingPrice(costPrices.get(2).add(profitValues.get(2)))
                        .build()
        );

        List<ProductComponent> productComponentsEntities = List.of(
                ComponentProductMapper.toProductComponentEntity(componentsEntity.get(0), quantities.get(0)),
                ComponentProductMapper.toProductComponentEntity(componentsEntity.get(1), quantities.get(1)),
                ComponentProductMapper.toProductComponentEntity(componentsEntity.get(2), quantities.get(2))
        );

        assertEquals(PricesCalculatorUtils.getCostPriceFromComponents(productComponentsEntities), expectedTotalCostPrice);
    }

}
