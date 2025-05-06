package com.example.cqrs_challenge.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "product_details")
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(precision = 10, scale = 2)
    private BigDecimal weight;

    @Column(columnDefinition = "jsonb")
    private String dimensions;

    @Column(columnDefinition = "text")
    private String materials;

    @Column(length = 100)
    private String countryOfOrigin;

    @Column(columnDefinition = "text")
    private String warrantyInfo;

    @Column(columnDefinition = "text")
    private String careInstructions;

    @Column(columnDefinition = "jsonb")
    private String additionalInfo;

}