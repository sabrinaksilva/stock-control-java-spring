package com.kappann.stockcontrol.service.item.component;

import com.kappann.stockcontrol.domain.dtos.items.componentItems.ComponentRequest;
import jakarta.validation.Valid;

public interface ItemComponentService {
    Long saveComponent (@Valid ComponentRequest componentRequest);
}
