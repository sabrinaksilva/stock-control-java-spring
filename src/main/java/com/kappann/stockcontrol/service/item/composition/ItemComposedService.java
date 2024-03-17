package com.kappann.stockcontrol.service.item.composition;

import com.kappann.stockcontrol.domain.dtos.items.compositions.CompositionRequest;
import jakarta.validation.Valid;

public interface ItemComposedService {
    Long createComposedItem (@Valid CompositionRequest compositionRequest);

}
