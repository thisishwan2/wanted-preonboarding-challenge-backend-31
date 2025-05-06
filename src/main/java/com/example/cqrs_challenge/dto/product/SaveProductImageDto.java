package com.example.cqrs_challenge.dto.product;

import com.example.cqrs_challenge.domain.entity.Product;
import com.example.cqrs_challenge.domain.entity.ProductImage;
import com.example.cqrs_challenge.domain.entity.ProductOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveProductImageDto {
    private String url;
    private String altText;
    private Boolean isPrimary;
    private Integer displayOrder;
    private Long optionId;

    public ProductImage toEntity(Product product, ProductOption productOption) {
        return ProductImage.builder()
                .product(product)
                .url(url)
                .altText(altText)
                .isPrimary(isPrimary)
                .displayOrder(displayOrder)
                .productOption(productOption)
                .build();
    }
}
