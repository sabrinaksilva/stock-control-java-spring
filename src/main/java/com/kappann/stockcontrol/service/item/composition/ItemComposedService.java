package com.kappann.stockcontrol.service.item.composition;

import com.kappann.stockcontrol.domain.dtos.items.composedItems.ComposedItemRequest;
import jakarta.validation.Valid;

public interface ItemComposedService {
    Long createComposedItem (@Valid ComposedItemRequest composedItemRequest);

}
