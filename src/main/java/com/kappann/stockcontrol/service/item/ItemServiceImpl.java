package com.kappann.stockcontrol.service.item;

import com.kappann.stockcontrol.service.item.component.ItemComponentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemComponentService componentService;

}
