package com.kappann.stockcontrol.mapper;

import com.kappann.stockcontrol.domain.models.products.Product;
import com.kappann.stockcontrol.domain.models.products.ProductComponent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ComponentProductMapper {

    public static ProductComponent toProductComponentEntity (Product componentProduct, Integer quantity) {
        return ProductComponent.builder()
                .componentProduct(componentProduct)
                .quantity(quantity)
                .build();
    }


}
