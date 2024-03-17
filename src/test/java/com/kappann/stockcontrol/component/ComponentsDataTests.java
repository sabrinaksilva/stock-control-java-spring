package com.kappann.stockcontrol.component;

import com.kappann.stockcontrol.domain.dtos.items.componentItems.ComponentRequest;
import com.kappann.stockcontrol.domain.models.items.StockItem;
import com.kappann.stockcontrol.fixtures.ComponentsTestsFixtures;
import com.kappann.stockcontrol.mapper.ItemMapper;
import com.kappann.stockcontrol.repository.item.ItemRepository;
import com.kappann.stockcontrol.utils.NumberTestsUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;

@AutoConfigureTestDatabase(connection = H2, replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class ComponentsDataTests {
    @Autowired
    ItemRepository repository;

    @Autowired
    TestEntityManager em;

    @Test
    void contextLoads () {
        Assertions.assertNotNull(em);
    }

    @Test
    @DisplayName("Should persist a new component in database")
    void shouldSaveAComponentFromRequestDTO () {
        ComponentRequest componentRequest = ComponentsTestsFixtures.buildComponentRequestDTO(NumberTestsUtils.getRandomPositive(), NumberTestsUtils.getRandomPositive());
        StockItem entity = ItemMapper.toStockItem(componentRequest);

        StockItem persistedEntity = repository.save(entity);

        assertNotNull(persistedEntity.getId());
        assertEquals(em.find(StockItem.class, persistedEntity.getId()), persistedEntity);
    }

}
