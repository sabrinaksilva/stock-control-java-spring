package com.kappann.stockcontrol.service.item.component;

import com.kappann.stockcontrol.domain.dtos.items.componentItems.ComponentRequest;
import com.kappann.stockcontrol.domain.models.items.StockItem;
import com.kappann.stockcontrol.mapper.ItemMapper;
import com.kappann.stockcontrol.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ItemComponentServiceImpl implements ItemComponentService {
    private final ItemRepository itemRepository;

    @Override
    public Long saveComponent (ComponentRequest componentRequest) {
        StockItem stockItem = ItemMapper.toStockItem(componentRequest);
        return (itemRepository.save(stockItem)).getId();
    }
}
