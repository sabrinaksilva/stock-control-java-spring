package com.kappann.stockcontrol.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;

import com.kappann.stockcontrol.domain.dtos.products.components.ComponentOfProductRequest;
import com.kappann.stockcontrol.domain.models.products.Product;
import com.kappann.stockcontrol.fixtures.ComponentsTestsFixtures;
import com.kappann.stockcontrol.mapper.ProductMapper;
import com.kappann.stockcontrol.repository.products.ProductComponentRepository;
import com.kappann.stockcontrol.repository.products.ProductRepository;
import com.kappann.stockcontrol.utils.NumberTestsUtils;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@AutoConfigureTestDatabase(connection = H2, replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class ComponentsDataTests {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  ProductComponentRepository productComponentRepository;

  @Autowired
  TestEntityManager em;

  @Test
  void contextLoads() {
    Assertions.assertNotNull(em);
  }

  @Test
  @DisplayName("Should persist a new component in database")
  void shouldSaveAComponentFromRequestDTO() {
    ComponentOfProductRequest componentOfProductRequest = ComponentsTestsFixtures.buildComponentRequestDTO(
        NumberTestsUtils.generateRandomBigDecimalPositive(),
        NumberTestsUtils.generateRandomBigDecimalPositive());
    Product entity = ProductMapper.toEntity(componentOfProductRequest,
        new Random().nextInt(0, 100));

    Product persistedEntity = productRepository.save(entity);
    assertNotNull(persistedEntity.getId());
    assertEquals(em.find(Product.class, persistedEntity.getId()), persistedEntity);
  }

}
