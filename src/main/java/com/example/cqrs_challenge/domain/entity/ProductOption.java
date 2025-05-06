package com.example.cqrs_challenge.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "product_options")
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_group_id", nullable = false)
    private ProductOptionGroup productOptionGroup;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(precision = 12, scale = 2)
    private BigDecimal additionalPrice = BigDecimal.ZERO;

    @Column(length = 100)
    private String sku;

    private Integer stock = 0;

    private Integer displayOrder = 0;

    @Builder
    public ProductOption(ProductOptionGroup productOptionGroup, String name, BigDecimal additionalPrice,
                         String sku, Integer stock, Integer displayOrder) {
        this.productOptionGroup = productOptionGroup;
        this.name = name;
        this.additionalPrice = additionalPrice;
        this.sku = sku;
        this.stock = stock;
        this.displayOrder = displayOrder;
    }
}