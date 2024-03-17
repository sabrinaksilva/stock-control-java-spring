package com.kappann.stockcontrol.domain.models.items.components;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "item_component")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @NotNull(message = "Stock Item must be provided")
    @JoinColumn(name = "component_item_id")
    private StockItem componentItem;


    @PositiveOrZero(message = "quantity of Item must be provided")
    private Integer quantity;

}
