package com.example.cqrs_challenge.service;

import com.example.cqrs_challenge.domain.entity.*;
import com.example.cqrs_challenge.dto.product.*;
import com.example.cqrs_challenge.global.exception.CustomException;
import com.example.cqrs_challenge.global.response.ErrorCode;
import com.example.cqrs_challenge.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final BrandRepository brandRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final ProductPriceRepository productPriceRepository;
    private final ProductOptionGroupRepository productOptionGroupRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductTagRepository productTagRepository;
    private final TagRepository tagRepository;

    @Transactional
    public CreateProductResponse createProduct(CreateProductRequest req){
        Seller seller = sellerRepository.findById(req.getSellerId()).orElseThrow(() -> new CustomException(
                ErrorCode.RESOURCE_NOT_FOUND, "판매자를 찾을 수 없습니다.", null
        ));

        Brand brand = brandRepository.findById(req.getBrandId()).orElseThrow(() -> new CustomException(
                ErrorCode.RESOURCE_NOT_FOUND, "브랜드를 찾을 수 없습니다.", null
        ));

        Product saveProduct = productRepository.save(req.toEntity(seller, brand));

        createProductDetail(saveProduct, req.getDetail());
        createProductPrice(saveProduct, req.getPrice());
        createProductCategoryList(saveProduct, req.getCategories());
        createProductOptionGroupList(saveProduct, req.getOptionGroups());
        createProductImageList(saveProduct, req.getImages());
        createProductTagList(saveProduct, req.getTags());

        return CreateProductResponse.from(saveProduct);
    }

    private void createProductDetail(Product product, SaveProductDetailDto detailDto) {
        ProductDetail productDetail = detailDto.toEntity(product);
        productDetailRepository.save(productDetail);
    }

    private void createProductPrice(Product product, SaveProductPriceDto priceDto) {
        ProductPrice productPrice = priceDto.toEntity(product);
        productPriceRepository.save(productPrice);
    }

    private void createProductCategoryList(Product product, List<SaveProductCategoryDto> categoryDtoList) {
        List<ProductCategory> productCategories = categoryDtoList.stream()
                .map(categoryDto -> {
                    Category category = categoryRepository.findById(categoryDto.getCategoryId())
                            .orElseThrow(() -> new CustomException(
                                    ErrorCode.RESOURCE_NOT_FOUND, "카테고리를 찾을 수 없습니다.", null));
                    return ProductCategory.builder()
                            .product(product)
                            .category(category)
                            .build();
                })
                .toList();

        productCategoryRepository.saveAll(productCategories);
    }

    private void createProductOptionGroupList(Product product, List<SaveProductOptionGroupDto> optionGroupDtoList) {
        for (SaveProductOptionGroupDto saveProductOptionGroupDto : optionGroupDtoList) {
            ProductOptionGroup productOptionGroup = saveProductOptionGroupDto.toEntity(product);
            productOptionGroupRepository.save(productOptionGroup);
            List<ProductOption> productOptions = saveProductOptionGroupDto.getOptions().stream()
                    .map(optionDto -> optionDto.toEntity(productOptionGroup))
                    .toList();

            productOptionRepository.saveAll(productOptions);
        }
    }

    private void createProductImageList(Product product, List<SaveProductImageDto> imageDtoList) {
        List<ProductImage> productImages = imageDtoList.stream()
                .map(imageDto -> {
                    ProductOption productOption = null;
                    if (imageDto.getOptionId() != null) {
                        productOption = productOptionRepository.findById(imageDto.getOptionId())
                                .orElseThrow(() -> new CustomException(
                                        ErrorCode.RESOURCE_NOT_FOUND, "옵션을 찾을 수 없습니다.", null));
                    }
                    return imageDto.toEntity(product, productOption);
                })
                .toList();

        productImageRepository.saveAll(productImages);
    }

    private void createProductTagList(Product product, List<Long> tags) {
        List<ProductTag> productTags = tags.stream()
                .map(tagId -> {
                    Tag tag = tagRepository.findById(tagId)
                            .orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND, "태그가 존재하지 않습니다.", null));
                    return ProductTag.builder()
                            .product(product)
                            .tag(tag)
                            .build();
                })
                .toList();

        productTagRepository.saveAll(productTags);
    }
}
