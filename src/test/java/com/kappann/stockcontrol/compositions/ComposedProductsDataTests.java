package com.kappann.stockcontrol.composedProducts;

import com.kappann.stockcontrol.domain.dtos.products.components.ComponentOfProductRequest;
import com.kappann.stockcontrol.domain.dtos.products.compositions.ProductComposedRequest;
import com.kappann.stockcontrol.domain.models.products.Product;
import com.kappann.stockcontrol.domain.models.products.ProductComponent;
import com.kappann.stockcontrol.fixtures.ComponentsTestsFixtures;
import com.kappann.stockcontrol.fixtures.ComposedProductsTestsFixtures;
import com.kappann.stockcontrol.mapper.ProductMapper;
import com.kappann.stockcontrol.repository.ProductRepository;
import com.kappann.stockcontrol.utils.NumberTestsUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;

@AutoConfigureTestDatabase(connection = H2, replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class ComposedProductsDataTests {
    @Autowired
    ProductRepository repository;

    @Autowired
    TestEntityManager em;


    @Test
    @DisplayName("Should not accept null components when creating a composed product and a list of components be provided")
    void shouldNotAcceptNullComponentsWhenCreatingAComposition () {
        List<Long> componentsIds = new ArrayList<>();

        ProductComposedRequest request = ComposedProductsTestsFixtures.buildComposedProductRequestRandomComponentsQuantities(componentsIds);
        List<ProductComponent> components = List.of(ProductComponent.builder().componentProduct(null).quantity(3).build());
        Product entity = ProductMapper.toEntity(request, components);
        assertThrows(Exception.class, () -> repository.save(entity));
    }

    @Test
    @DisplayName("Should not accept null quantity of some component when creating a composed product with a list of components provided")
    void shouldNotAcceptNullQuantitiesAtComponentsWhenCreatingAComposition () {
        Product component1 = createSaveRandomComponent();
        Product component2 = createSaveRandomComponent();

        List<Long> componentsIds = new ArrayList<>(List.of(component1.getId(), component2.getId()));
        ProductComposedRequest request = ComposedProductsTestsFixtures.buildComposedProductRequestRandomComponentsQuantities(componentsIds);

        List<ProductComponent> components = List.of(
                ProductComponent.builder().componentProduct(component1).quantity(3).build(),
                ProductComponent.builder().componentProduct(component2).quantity(null).build());

        Product entityWithNullQuantity = ProductMapper.toEntity(request, components);
        assertThrows(Exception.class, () -> repository.save(entityWithNullQuantity));
    }

    @Test
    @DisplayName("Should not accept negative number in the quantity of some component when creating a composed product with a list of components provided")
    void shouldJustAcceptPositiveNonNullQuantitiesAtComponentsWhenCreatingAComposition () {
        Product component1 = createSaveRandomComponent();
        Product component2 = createSaveRandomComponent();

        List<Long> componentsIds = new ArrayList<>(List.of(component1.getId(), component2.getId()));
        ProductComposedRequest request = ComposedProductsTestsFixtures.buildComposedProductRequestRandomComponentsQuantities(componentsIds);

        List<ProductComponent> components = List.of(
                ProductComponent.builder().componentProduct(component1).quantity(-3).build(),
                ProductComponent.builder().componentProduct(component2).quantity(4).build());

        Product entityWithNegativeQuantity = ProductMapper.toEntity(request, components);
        assertThrows(Exception.class, () -> repository.save(entityWithNegativeQuantity));
    }

    private Product createSaveRandomComponent () {
        ComponentOfProductRequest componentRequest1 = ComponentsTestsFixtures.buildComponentRequestDTO(NumberTestsUtils.generateRandomBigDecimalPositive(), NumberTestsUtils.generateRandomBigDecimalPositive());
        Product component1 = ProductMapper.toEntity(componentRequest1);
        component1 = repository.save(component1);
        return component1;
    }


}
