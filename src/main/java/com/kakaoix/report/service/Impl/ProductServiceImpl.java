package com.kakaoix.report.service.Impl;

import com.kakaoix.report.domain.Product;
import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.model.Pagination;
import com.kakaoix.report.repository.ProductRepository;
import com.kakaoix.report.service.ProductService;
import com.kakaoix.report.utils.ResponseMessage;
import com.kakaoix.report.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by ds on 2018-08-31.
 */

/**
 * 상품 서비스 구현체
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    /**
     * Repository 의존성 주입
     */
    @Autowired
    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * 상품 전체 조회
     *
     * @param pagination 페이지네이션
     * @return 상품 리스트
     */
    @Override
    public DefaultRes<Iterable<Product>> findAll(final Pagination pagination) {
        Iterable<Product> productIterable = productRepository.findAll(pagination);
        return DefaultRes.<Iterable<Product>>builder()
                .statusCode(StatusCode.OK)
                .responseMessage(ResponseMessage.READ_PRODUCT_LIST)
                .responseData(productIterable)
                .build();
    }

    /**
     * 상품 정보 조회
     *
     * @param productIdx 상품 고유 IDX
     * @return 상품 정보
     */
    @Override
    public DefaultRes<Product> findOne(final int productIdx) {
        final Optional<Product> product = getProduct(productIdx);
        if (product.isPresent()) {
            return DefaultRes.<Product>builder()
                    .statusCode(StatusCode.OK)
                    .responseMessage(ResponseMessage.READ_PRODUCT)
                    .responseData(product.get())
                    .build();
        }
        return DefaultRes.<Product>builder()
                .statusCode(StatusCode.NOT_FOUND)
                .responseMessage(ResponseMessage.NOT_FOUND_PRODUCT)
                .build();
    }

    /**
     * 상품 정보 조회
     *
     * @param productIdx 상품 고유 IDX
     * @return 상품
     */
    @Override
    public Optional<Product> getProduct(final int productIdx) {
        return productRepository.findById(productIdx);
    }

}
