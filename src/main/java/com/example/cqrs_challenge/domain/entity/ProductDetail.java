package com.example.cqrs_challenge.domain.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

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

    @Type(JsonType.class)
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

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private String additionalInfo;

    @Builder
    public ProductDetail(Product product, BigDecimal weight, String dimensions, String materials,
                         String countryOfOrigin, String warrantyInfo, String careInstructions,
                         String additionalInfo) {
        this.product = product;
        this.weight = weight;
        this.dimensions = dimensions;
        this.materials = materials;
        this.countryOfOrigin = countryOfOrigin;
        this.warrantyInfo = warrantyInfo;
        this.careInstructions = careInstructions;
        this.additionalInfo = additionalInfo;
    }
}