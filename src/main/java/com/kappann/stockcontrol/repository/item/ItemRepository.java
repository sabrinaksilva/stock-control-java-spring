package com.kappann.stockcontrol.repository.item;

import com.kappann.stockcontrol.domain.models.items.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<StockItem, Long> {
}
