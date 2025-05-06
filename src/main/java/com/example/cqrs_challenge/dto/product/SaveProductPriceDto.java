package com.example.cqrs_challenge.dto.product;

import com.example.cqrs_challenge.domain.entity.Product;
import com.example.cqrs_challenge.domain.entity.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveProductPriceDto {

    private BigDecimal basePrice;
    private BigDecimal salePrice;
    private BigDecimal costPrice;
    private String currency;
    private BigDecimal taxRate;

    public ProductPrice toEntity(Product product) {
        return ProductPrice.builder()
                .basePrice(basePrice)
                .salePrice(salePrice)
                .costPrice(costPrice)
                .currency(currency)
                .taxRate(taxRate)
                .product(product)
                .build();
    }

}
