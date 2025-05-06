package com.example.cqrs_challenge.dto.product;

import com.example.cqrs_challenge.domain.entity.Product;
import com.example.cqrs_challenge.domain.entity.ProductOptionGroup;
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
public class SaveProductOptionGroupDto {
    public String name;
    public Integer displayOrder;
    public List<SaveProductOptionDto> options = new ArrayList<>();

    public ProductOptionGroup toEntity(Product product) {
        return ProductOptionGroup.builder()
                .name(name)
                .displayOrder(displayOrder)
                .product(product)
                .build();
    }
}
