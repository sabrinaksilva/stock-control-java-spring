package com.kappann.stockcontrol.domain.models.products;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "product_component")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "ProductC omponent must be provided")
    @JoinColumn(name = "product_component_id")
    private Product componentProduct;


    @PositiveOrZero(message = "quantity must be provided")
    private Integer quantity;

}