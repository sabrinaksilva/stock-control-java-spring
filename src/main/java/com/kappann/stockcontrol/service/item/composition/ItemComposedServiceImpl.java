package com.kappann.stockcontrol.service.item.composition;

import com.kappann.stockcontrol.domain.dtos.items.compositions.CompositionRequest;
import com.kappann.stockcontrol.domain.models.items.components.ItemComponent;
import com.kappann.stockcontrol.domain.models.items.components.StockItem;
import com.kappann.stockcontrol.mapper.ItemComponentMapper;
import com.kappann.stockcontrol.repository.item.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class ItemComposedServiceImpl implements ItemComposedService {
    private final ItemRepository itemRepository;

    @Override
    public Long createComposedItem (CompositionRequest compositionRequest) {
        List<ItemComponent> components = new ArrayList<>();
        compositionRequest.getComponents().forEach(component -> {
            StockItem itemComponent = itemRepository
                    .findById(component.getItemComponentId())
                    .orElseThrow(() -> new EntityNotFoundException("Component item not found! Id = " + component.getItemComponentId()));
            components.add(ItemComponentMapper.toStockItemComponent(itemComponent, component.getRequiredQuantity()));
        });

        StockItem composedItem = StockItem.builder()
                .name(compositionRequest.getName())
                .description(compositionRequest.getDescription())
                .components(components).build();

        return itemRepository.save(composedItem).getId();
    }
}