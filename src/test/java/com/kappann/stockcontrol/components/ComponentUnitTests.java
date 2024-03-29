package com.kappann.stockcontrol.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.kappann.stockcontrol.domain.dtos.products.components.ComponentOfProductRequest;
import com.kappann.stockcontrol.domain.models.products.Product;
import com.kappann.stockcontrol.fixtures.ComponentsTestsFixtures;
import com.kappann.stockcontrol.mapper.ProductMapper;
import com.kappann.stockcontrol.utils.NumberTestsUtils;
import java.math.BigDecimal;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ComponentUnitTests {


  @Test
  @DisplayName("When mapping the DTO from request to the entity to be saved, must set all params")
  void testMapFromRequestDtoToEntity() {
    BigDecimal costPrice = NumberTestsUtils.generateRandomBigDecimalPositive();
    BigDecimal profitValue = NumberTestsUtils.generateRandomBigDecimalPositive();

    ComponentOfProductRequest requestDTO = ComponentsTestsFixtures.buildComponentRequestDTO(
        costPrice, profitValue);

    Integer quantity = new Random().nextInt(0, 100);
    Product entity = ProductMapper.toEntity(requestDTO, quantity);

    assertEquals(requestDTO.getName(), entity.getName());
    assertEquals(requestDTO.getDescription(), entity.getDescription());
    assertTrue(entity.getComponents().isEmpty());
    assertEquals(requestDTO.getCostPrice(), entity.getCostPrice());
    assertEquals(requestDTO.getCostPrice().add(profitValue), entity.getSellingPrice());
    assertNull(entity.getId());
    assertFalse(entity.isComposition());
    assertEquals(entity.getCurrentQuantityInStock(), quantity);
  }


}
