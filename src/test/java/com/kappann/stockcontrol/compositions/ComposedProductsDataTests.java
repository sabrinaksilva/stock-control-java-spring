package com.kappann.stockcontrol.compositions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;

import com.kappann.stockcontrol.domain.dtos.products.components.ComponentOfProductRequest;
import com.kappann.stockcontrol.domain.dtos.products.compositions.ProductComposedRequest;
import com.kappann.stockcontrol.domain.models.products.Product;
import com.kappann.stockcontrol.domain.models.products.ProductComponent;
import com.kappann.stockcontrol.fixtures.ComponentsTestsFixtures;
import com.kappann.stockcontrol.fixtures.ComposedProductsTestsFixtures;
import com.kappann.stockcontrol.mapper.ProductMapper;
import com.kappann.stockcontrol.repository.products.ProductComponentRepository;
import com.kappann.stockcontrol.repository.products.ProductRepository;
import com.kappann.stockcontrol.service.products.composedProducts.ProductComposedService;
import com.kappann.stockcontrol.service.products.composedProducts.ProductComposedServiceImpl;
import com.kappann.stockcontrol.utils.NumberTestsUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@AutoConfigureTestDatabase(connection = H2, replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
public class ComposedProductsDataTests {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  ProductComponentRepository productComponentRepository;

  ProductComposedService productComposedService;

  @BeforeAll
  void initializeServices() {
    this.productComposedService = new ProductComposedServiceImpl(productRepository);

  }

  @Test
  @DisplayName("Should not accept null components when creating a composed product and a list of components be provided")
  void shouldNotAcceptNullComponentsWhenCreatingAComposition() {
    List<Long> componentsIds = new ArrayList<>();

    ProductComposedRequest request = ComposedProductsTestsFixtures.buildComposedProductRequestRandomComponentsQuantities(
        componentsIds);
    Set<ProductComponent> components = Set.of(
        ProductComponent.builder().componentProduct(null).requiredQuantity(3).build());
    Product entity = ProductMapper.toEntity(request, components);
    assertThrows(Exception.class, () -> productRepository.save(entity));
  }

  @Test
  @DisplayName("Should not accept null quantity of some component when creating a composed product with a list of components provided")
  void shouldNotAcceptNullQuantitiesAtComponentsWhenCreatingAComposition() {
    Product component1 = createSaveRandomComponent(new Random().nextInt(0, 50));
    Product component2 = createSaveRandomComponent(new Random().nextInt(0, 50));

    List<Long> componentsIds = new ArrayList<>(List.of(component1.getId(), component2.getId()));
    ProductComposedRequest request = ComposedProductsTestsFixtures.buildComposedProductRequestRandomComponentsQuantities(
        componentsIds);

    Set<ProductComponent> components = Set.of(
        ProductComponent.builder().componentProduct(component1).requiredQuantity(3).build(),
        ProductComponent.builder().componentProduct(component2).requiredQuantity(null).build());

    Product entityWithNullQuantity = ProductMapper.toEntity(request, components);
    assertThrows(Exception.class, () -> productRepository.save(entityWithNullQuantity));
  }

  @Test
  @DisplayName("Should not accept negative number in the quantity of some component when creating a composed product with a list of components provided")
  void shouldJustAcceptPositiveNonNullQuantitiesAtComponentsWhenCreatingAComposition() {
    Product component1 = createSaveRandomComponent(new Random().nextInt(0, 50));
    Product component2 = createSaveRandomComponent(new Random().nextInt(0, 50));

    List<Long> componentsIds = new ArrayList<>(List.of(component1.getId(), component2.getId()));
    ProductComposedRequest request = ComposedProductsTestsFixtures.buildComposedProductRequestRandomComponentsQuantities(
        componentsIds);

    Set<ProductComponent> components = Set.of(
        ProductComponent.builder().componentProduct(component1).requiredQuantity(-3).build(),
        ProductComponent.builder().componentProduct(component2).requiredQuantity(4).build());

    Product entityWithNegativeQuantity = ProductMapper.toEntity(request, components);
    assertThrows(Exception.class, () -> productRepository.save(entityWithNegativeQuantity));
  }

  private Product buildRandomComponent(Integer quantity) {
    ComponentOfProductRequest componentRequest = ComponentsTestsFixtures.buildComponentRequestDTO(
        NumberTestsUtils.generateRandomBigDecimalPositive(),
        NumberTestsUtils.generateRandomBigDecimalPositive());
    return ProductMapper.toEntity(componentRequest, quantity);
  }

  private Product createSaveRandomComponent(Integer quantity) {
    return saveProductWithComponents(
        buildRandomComponent(quantity), "Component product was not saved");
  }

  @Test
  @DisplayName("If parent product have any components, must return all of them")
  void shouldReturnSetOfComponentsOfParentProduct() {
    Product component1 = createSaveRandomComponent(new Random().nextInt(0, 50));
    Product component2 = createSaveRandomComponent(new Random().nextInt(0, 50));

    List<Long> componentsIds = new ArrayList<>(List.of(component1.getId(), component2.getId()));
    ProductComposedRequest request = ComposedProductsTestsFixtures.buildComposedProductRequestRandomComponentsQuantities(
        componentsIds);

    Set<ProductComponent> components = Set.of(
        ProductComponent.builder().componentProduct(component1).requiredQuantity(1).build(),
        ProductComponent.builder().componentProduct(component2).requiredQuantity(4).build());

    Product composition = saveProductWithComponents(ProductMapper.toEntity(request, components),
        "Parent product was not saved");

    composition = productRepository.findByIdFetchComponents(composition.getId()).orElseThrow();
    Set<ProductComponent> productComponents = productComponentRepository.findAllByParentProduct(
        composition);

    assertNotNull(productComponents);
    assertTrue(componentsIds.containsAll(productComponents.stream()
        .map(productComponent -> productComponent.getComponentProduct().getId()).toList()));

  }


  @Test
  @DisplayName("If parent product DOES NOT HAVE any components, must return empty set of components")
  void shouldReturnEmptySetOfComponentsOfParentProduct() {
    List<Long> componentsIds = new ArrayList();
    ProductComposedRequest request = ComposedProductsTestsFixtures.buildComposedProductRequestRandomComponentsQuantities(
        componentsIds);

    Set<ProductComponent> components = new HashSet<>();

    Product composition = saveProductWithComponents(ProductMapper.toEntity(request, components),
        "Parent product was not saved");

    composition = productRepository.findByIdFetchComponents(composition.getId()).orElseThrow();
    Set<ProductComponent> productComponents = productComponentRepository.findAllByParentProduct(
        composition);

    assertTrue(productComponents.isEmpty());

  }


  private Product saveProductWithComponents(Product product, String message) {
    Product finalProduct = product;
    product.getComponents().forEach(productComponent -> productComponent.setParentProduct(
        finalProduct));
    product = productRepository.save(product);
    assertNotNull(product.getId(), message);
    return product;
  }

  @Test
  void shouldReturnZeroProductsCanBeProducedByComponentsQuantities() {
    Product component1 = createSaveRandomComponent(new Random().nextInt(0, 3));
    Product component2 = createSaveRandomComponent(new Random().nextInt(0, 3));

    List<Long> componentsIds = new ArrayList<>(List.of(component1.getId(), component2.getId()));
    ProductComposedRequest request = ComposedProductsTestsFixtures.buildComposedProductRequestRandomComponentsQuantities(
        componentsIds);

    Set<ProductComponent> components = Set.of(
        ProductComponent.builder().componentProduct(component1).requiredQuantity(1).build(),
        ProductComponent.builder().componentProduct(component2).requiredQuantity(4).build());

    Product parentProduct = saveProductWithComponents(ProductMapper.toEntity(request, components),
        "Parent product was not saved");

    parentProduct = productRepository.findByIdFetchComponents(parentProduct.getId()).orElseThrow();
    Set<ProductComponent> productComponents = productComponentRepository.findAllByParentProduct(
        parentProduct);
    assertEquals(0,
        productComposedService.findAmountPossibleToProduceFromComponentsInStock(parentProduct,
            components));
  }


  @Test
  void shouldReturnTwoProductsCanBeProducedByComponentsQuantities() {
    Product component1 = createSaveRandomComponent(10);
    Product component2 = createSaveRandomComponent(6);

    List<Long> componentsIds = new ArrayList<>(List.of(component1.getId(), component2.getId()));
    ProductComposedRequest request = ComposedProductsTestsFixtures.buildComposedProductRequestRandomComponentsQuantities(
        componentsIds);

    Set<ProductComponent> components = Set.of(
        ProductComponent.builder().componentProduct(component1).requiredQuantity(5).build(),
        ProductComponent.builder().componentProduct(component2).requiredQuantity(2).build());

    Product parentProduct = saveProductWithComponents(ProductMapper.toEntity(request, components),
        "Parent product was not saved");

    parentProduct = productRepository.findByIdFetchComponents(parentProduct.getId()).orElseThrow();
    Set<ProductComponent> productComponents = productComponentRepository.findAllByParentProduct(
        parentProduct);
    assertEquals(2,
        productComposedService.findAmountPossibleToProduceFromComponentsInStock(parentProduct,
            components));
  }

}
