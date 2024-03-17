package com.kappann.stockcontrol.domain.models.items.components;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@Table(name = "stock_item")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Name is required!")
    private String name;

    private String description;

    @OneToMany(mappedBy = "componentItem", fetch = FetchType.LAZY)
    private List<ItemComponent> components = new ArrayList<>();

    @Column
    @NotNull(message = "Cost price for item must be provided!")
    private BigDecimal costPrice;

    @NotNull(message = "Sale price must be provided!")
    private BigDecimal sellingPrice;


    public boolean isComposition () {
        return this.components != null && !this.components.isEmpty();
    }

}
