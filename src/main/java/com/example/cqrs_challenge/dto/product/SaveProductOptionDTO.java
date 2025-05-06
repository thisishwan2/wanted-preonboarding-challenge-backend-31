package com.example.cqrs_challenge.dto.product;

import com.example.cqrs_challenge.domain.entity.ProductOption;
import com.example.cqrs_challenge.domain.entity.ProductOptionGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveProductOptionDTO {
    private String name;
    private BigDecimal additionalPrice;
    private String sku;
    private Integer stock;
    private Integer displayOrder;

    public ProductOption toEntity(ProductOptionGroup productOptionGroup) {
        return ProductOption.builder()
                .name(name)
                .additionalPrice(additionalPrice)
                .sku(sku)
                .stock(stock)
                .displayOrder(displayOrder)
                .productOptionGroup(productOptionGroup)
                .build();
    }
}
