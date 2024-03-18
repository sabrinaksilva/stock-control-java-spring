package com.kappann.stockcontrol.service.stock;

import com.kappann.stockcontrol.domain.dtos.stock.StockAvailabilityResponse;

public interface StockService {

  StockAvailabilityResponse findQuantityInStockReadyToUseAndAvailabilityToProduce(Long productId);
}
