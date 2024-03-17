package com.kappann.stockcontrol.component;

import com.kappann.stockcontrol.domain.dtos.items.componentItems.ComponentRequest;
import com.kappann.stockcontrol.domain.models.items.StockItem;
import com.kappann.stockcontrol.fixtures.ComponentsTestsFixtures;
import com.kappann.stockcontrol.mapper.ItemMapper;
import com.kappann.stockcontrol.utils.NumberTestsUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ComponentUnitTests {


    @Test
    @DisplayName("When mapping the DTO from request to the entity to be saved, must set all params")
    void testMapFromRequestDtoToEntity () {
        BigDecimal costPrice = NumberTestsUtils.getRandomPositive();
        BigDecimal profitValue = NumberTestsUtils.getRandomPositive();

        ComponentRequest requestDTO = ComponentsTestsFixtures.buildComponentRequestDTO(costPrice, profitValue);

        StockItem entity = ItemMapper.toStockItem(requestDTO);

        assertEquals(requestDTO.getName(), entity.getName());
        assertEquals(requestDTO.getDescription(), entity.getDescription());
        assertEquals(new ArrayList<>(), entity.getComponents());
        assertEquals(requestDTO.getCostPrice(), entity.getCostPrice());
        assertEquals(requestDTO.getCostPrice().add(profitValue), entity.getSellingPrice());
        assertNull(entity.getId());
        assertFalse(entity.isComposition());
    }


}
