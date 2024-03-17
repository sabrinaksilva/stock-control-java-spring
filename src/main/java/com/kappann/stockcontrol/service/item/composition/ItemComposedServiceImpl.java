package com.kappann.stockcontrol.service.item.composition;

import com.kappann.stockcontrol.domain.dtos.items.composedItems.ComposedItemRequest;
import com.kappann.stockcontrol.domain.models.items.ItemComponent;
import com.kappann.stockcontrol.domain.models.items.StockItem;
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
    public Long createComposedItem (ComposedItemRequest composedItemRequest) {
        List<ItemComponent> components = new ArrayList<>();
        addComponentsOfComposedItem(composedItemRequest, components);

        StockItem composedItem = StockItem.builder()
                .name(composedItemRequest.getName())
                .description(composedItemRequest.getDescription())
                .components(components).build();

        return itemRepository.save(composedItem).getId();
    }

    private void addComponentsOfComposedItem (ComposedItemRequest composedItemRequest, List<ItemComponent> components) {
        composedItemRequest.getComponents().forEach(component -> {
            StockItem itemComponent = itemRepository
                    .findById(component.getItemComponentId())
                    .orElseThrow(() -> new EntityNotFoundException("Component item not found! Id = " + component.getItemComponentId()));
            components.add(ItemComponentMapper.toStockItemComponent(itemComponent, component.getRequiredQuantity()));
        });
    }
}