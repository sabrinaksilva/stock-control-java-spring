package com.kappann.stockcontrol.domain.models.products;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

  @OneToMany(mappedBy = "parentProduct", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
      CascadeType.MERGE, CascadeType.DETACH})
  @Builder.Default
  private Set<ProductComponent> components = new HashSet<>();


  @Column
  @Builder.Default
  private BigDecimal costPrice = BigDecimal.ZERO;

  @NotNull(message = "Sale price must be provided!")
  private BigDecimal sellingPrice;

  @Column
  @Builder.Default
  private Integer currentQuantityInStock = 0;

  public boolean isComposition() {
    return this.components != null && !this.components.isEmpty();
  }

}
