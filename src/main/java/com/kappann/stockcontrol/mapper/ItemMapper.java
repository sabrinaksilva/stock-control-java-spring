package com.kappann.stockcontrol.mapper;

import com.kappann.stockcontrol.domain.dtos.items.components.ComponentRequest;
import com.kappann.stockcontrol.domain.models.items.components.StockItem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemMapper {

    public static StockItem toStockItem (ComponentRequest request) {
        return StockItem
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .components(new ArrayList<>())
                .costPrice(request.getCostPrice())
                .sellingPrice(request
                        .getCostPrice().add(request.getProfitValue()))
                .build();
    }
}
