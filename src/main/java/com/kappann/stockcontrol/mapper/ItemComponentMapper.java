package com.kappann.stockcontrol.mapper;

import com.kappann.stockcontrol.domain.models.items.ItemComponent;
import com.kappann.stockcontrol.domain.models.items.StockItem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemComponentMapper {

    public static ItemComponent toStockItemComponent (StockItem componentItem, Integer quantity) {
        return ItemComponent.builder()
                .componentItem(componentItem)
                .quantity(quantity)
                .build();
    }
}
