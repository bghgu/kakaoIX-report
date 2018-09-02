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

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * 상품 전체 조회
     * @param pagination
     * @return
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
     * 상품 세부 조회
     * @param productIdx
     * @return
     */
    @Override
    public DefaultRes<Product> findOne(final int productIdx) {
        final Optional<Product> product = productRepository.findById(productIdx);
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
     *
     * @param productIdx
     * @return
     */
    @Override
    public Optional<Product> getProduct(final int productIdx) {
        return productRepository.findById(productIdx);
    }

}
