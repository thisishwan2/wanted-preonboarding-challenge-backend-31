package com.example.cqrs_challenge.dto.product;

import com.example.cqrs_challenge.domain.entity.Product;
import com.example.cqrs_challenge.domain.entity.ProductDetail;
import com.example.cqrs_challenge.global.exception.CustomException;
import com.example.cqrs_challenge.global.response.ErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveProductDetailDto {
    private BigDecimal weight;
    private DimensionsDto dimensions;
    private String materials;
    private String countryOfOrigin;
    private String warrantyInfo;
    private String careInstructions;
    private AdditionalInfoDto additionalInfo;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DimensionsDto {
        private int width;
        private int height;
        private int depth;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AdditionalInfoDto {
        private boolean assemblyRequired;
        private String assemblyTime;
    }

    public ProductDetail toEntity(Product product) {
        // Json 변환을 위한 ObjectMapper 생성
        ObjectMapper objectMapper = new ObjectMapper();
        String dimensionsJson = null;
        String additionalInfoJson = null;
        try {
            dimensionsJson = objectMapper.writeValueAsString(dimensions);
            additionalInfoJson = objectMapper.writeValueAsString(additionalInfo);
        }catch (JsonProcessingException e) {
            throw new CustomException(
                    ErrorCode.JSON_CONVERT_ERROR,
                    "JSON 변환 중 오류 발생",
                    null
            );
        }

        return ProductDetail.builder()
                .product(product)
                .weight(weight)
                .dimensions(dimensionsJson)
                .materials(materials)
                .countryOfOrigin(countryOfOrigin)
                .warrantyInfo(warrantyInfo)
                .careInstructions(careInstructions)
                .additionalInfo(additionalInfoJson)
                .build();
    }
}
