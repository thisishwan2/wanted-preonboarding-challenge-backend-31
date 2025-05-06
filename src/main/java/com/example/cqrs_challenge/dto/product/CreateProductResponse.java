package com.example.cqrs_challenge.dto.product;

import com.example.cqrs_challenge.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductResponse {
    private Long id;
    private String name;
    private String slug;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CreateProductResponse from(Product product) {
        return CreateProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .slug(product.getSlug())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
