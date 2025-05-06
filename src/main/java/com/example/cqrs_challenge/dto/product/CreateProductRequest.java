package com.example.cqrs_challenge.dto.product;

import com.example.cqrs_challenge.domain.entity.Brand;
import com.example.cqrs_challenge.domain.entity.Product;
import com.example.cqrs_challenge.domain.entity.Seller;
import com.example.cqrs_challenge.domain.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductRequest {

    private String name;
    private String slug;
    private String shortDescription;
    private String fullDescription;
    private Long sellerId;
    private Long brandId;
    private ProductStatus status;
    private SaveProductDetailDto detail;
    private SaveProductPriceDto price;
    private List<SaveProductCategoryDto> categories = new ArrayList<>();
    private List<SaveProductOptionGroupDto> optionGroups = new ArrayList<>();
    private List<SaveProductImageDto> images = new ArrayList<>();
    private List<Long> tags;

    public Product toEntity(Seller seller, Brand brand) {
        return Product.builder()
                .name(name)
                .slug(slug)
                .shortDescription(shortDescription)
                .fullDescription(fullDescription)
                .seller(seller)
                .brand(brand)
                .status(status)
                .build();
    }
}
