package com.kappann.stockcontrol.composedProducts;

import com.kappann.stockcontrol.domain.dtos.products.compositions.ProductComposedRequest;
import com.kappann.stockcontrol.domain.models.products.Product;
import com.kappann.stockcontrol.domain.models.products.ProductComponent;
import com.kappann.stockcontrol.fixtures.ComposedProductsTestsFixtures;
import com.kappann.stockcontrol.mapper.ProductMapper;
import com.kappann.stockcontrol.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;

@AutoConfigureTestDatabase(connection = H2, replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class ComposedProductsDataTests {
    @Autowired
    ProductRepository repository;

    @Autowired
    TestEntityManager em;


    @Test
    @DisplayName("Should not accept null or empty components when creating a composed product")
    void shouldNotAcceptNullOrEmptyListOfComponentsWhenCreatingAComposition () {
        List<Long> componentsIds = new ArrayList<>();

        ProductComposedRequest request = ComposedProductsTestsFixtures.buildComposedProductRequestRandomComponentsQuantities(componentsIds);
        List<ProductComponent> components = new ArrayList<>();
        Product entity = ProductMapper.toEntity(request, components);


    }
}
