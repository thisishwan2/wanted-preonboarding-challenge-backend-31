package com.example.cqrs_challenge.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "product_prices")
public class ProductPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal basePrice;

    @Column(precision = 12, scale = 2)
    private BigDecimal salePrice;

    @Column(precision = 12, scale = 2)
    private BigDecimal costPrice;

    @Column(length = 3)
    private String currency = "KRW";

    @Column(precision = 5, scale = 2)
    private BigDecimal taxRate;

    @Builder
    public ProductPrice(Product product, BigDecimal basePrice, BigDecimal salePrice, BigDecimal costPrice,
                        String currency, BigDecimal taxRate) {
        this.product = product;
        this.basePrice = basePrice;
        this.salePrice = salePrice;
        this.costPrice = costPrice;
        this.currency = currency;
        this.taxRate = taxRate;
    }

}
