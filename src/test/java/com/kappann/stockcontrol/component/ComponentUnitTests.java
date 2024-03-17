package com.kappann.stockcontrol.component;

import com.kappann.stockcontrol.component.fixtures.ComponentsTestsFixtures;
import com.kappann.stockcontrol.domain.dtos.items.components.ComponentRequest;
import com.kappann.stockcontrol.domain.models.items.components.StockItem;
import com.kappann.stockcontrol.helpers.NumberTestsHelper;
import com.kappann.stockcontrol.mapper.ItemMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComponentUnitTests {

    private final List<String> DEFAULT_NAMES = List.of("Component test name 1", "Component test name 2", "Component test name 3");
    private final List<String> DEFAULT_DESCRIPTIONS = List.of("Component description 1", "Component description 2", "Component description 3");


    @Test
    @DisplayName("When mapping the DTO from request to the entity to be saved, must set all params")
    void testMapFromRequestDtoToEntity () {
        BigDecimal costPrice = NumberTestsHelper.getRandomPositive();
        BigDecimal profitValue = NumberTestsHelper.getRandomPositive();

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
