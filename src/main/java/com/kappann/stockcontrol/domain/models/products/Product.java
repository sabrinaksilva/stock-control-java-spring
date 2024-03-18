package com.kappann.stockcontrol.domain.models.products;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Name is required!")
    private String name;

    private String description;

    @OneToMany(mappedBy = "componentProduct", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductComponent> components = new ArrayList<>();

    @Column
    @JsonSetter(nulls = Nulls.SKIP)
    private BigDecimal costPrice = BigDecimal.ZERO;

    @NotNull(message = "Sale price must be provided!")
    private BigDecimal sellingPrice;


    public boolean isComposition () {
        return this.components != null && !this.components.isEmpty();
    }

}
