package com.kappann.stockcontrol.service.products.composedProducts;

import com.kappann.stockcontrol.domain.dtos.products.compositions.ProductComposedRequest;
import com.kappann.stockcontrol.domain.models.products.Product;
import com.kappann.stockcontrol.domain.models.products.ProductComponent;
import com.kappann.stockcontrol.mapper.ComponentProductMapper;
import com.kappann.stockcontrol.repository.ProductRepository;
import com.kappann.stockcontrol.utils.prices.PricesCalculatorUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class ProductComposedServiceImpl implements ProductComposedService {
    private final ProductRepository productRepository;

    @Override
    public Long saveComposedProduct (ProductComposedRequest productComposedRequest) {
        List<ProductComponent> componentsEntities = new ArrayList<>();
        addComponents(productComposedRequest, componentsEntities);
        BigDecimal costPrice = PricesCalculatorUtils.getCostPriceFromComponents(componentsEntities);

        Product composedProduct = Product.builder()
                .name(productComposedRequest.getName())
                .description(productComposedRequest.getDescription())
                .components(componentsEntities)
                .costPrice(costPrice)
                .sellingPrice(costPrice.add(productComposedRequest.getProfitValue()))
                .build();

        return productRepository.save(composedProduct).getId();
    }


    private void addComponents (ProductComposedRequest productComposedRequest, List<ProductComponent> components) {
        productComposedRequest.getComponents().forEach(component -> {
            Product productComponent = productRepository
                    .findById(component.getComponentId())
                    .orElseThrow(() -> new EntityNotFoundException("Component product not found! Id = " + component.getComponentId()));
            components.add(ComponentProductMapper.toProductComponentEntity(productComponent, component.getRequiredQuantity()));
        });
    }
}