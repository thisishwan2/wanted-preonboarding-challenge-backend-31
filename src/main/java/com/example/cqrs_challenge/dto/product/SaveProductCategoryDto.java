package com.example.cqrs_challenge.dto.product;

import com.example.cqrs_challenge.domain.entity.Category;
import com.example.cqrs_challenge.domain.entity.Product;
import com.example.cqrs_challenge.domain.entity.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveProductCategoryDto {

    private Long categoryId;
    private Boolean isPrimary;

    public ProductCategory toEntity(Product product, Category category) {
        return ProductCategory.builder()
                .isPrimary(isPrimary)
                .product(product)
                .category(category)
                .build();
    }
}
