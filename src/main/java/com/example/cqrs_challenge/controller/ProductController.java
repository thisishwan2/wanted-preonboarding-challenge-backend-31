package com.example.cqrs_challenge.controller;

import com.example.cqrs_challenge.dto.product.CreateProductRequest;
import com.example.cqrs_challenge.dto.product.CreateProductResponse;
import com.example.cqrs_challenge.global.response.ApiResponse;
import com.example.cqrs_challenge.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    /**
     * 상품 등록
     */
    @PostMapping("/products")
    public ApiResponse<CreateProductResponse> createProduct(@RequestBody CreateProductRequest req){
        CreateProductResponse res = productService.createProduct(req);
        return ApiResponse.<CreateProductResponse>builder()
                .success(true)
                .data(res)
                .message("상품이 성공적으로 등록되었습니다.")
                .build();
    }

    /**
     * 상품 목록 조회
     */
//    @GetMapping("/products")


    /**
     * 상품 상세 조회
     */

    /**
     * 상품 수정
     */

    /**
     * 상품 삭제
     */
}
