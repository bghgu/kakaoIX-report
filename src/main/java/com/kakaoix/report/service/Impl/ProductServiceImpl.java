package com.kakaoix.report.service.Impl;

import com.kakaoix.report.domain.Product;
import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.repository.ProductRepository;
import com.kakaoix.report.service.ProductService;
import com.kakaoix.report.utils.ResponseMessage;
import com.kakaoix.report.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
     * 페이지네이션 작업 안함
     * @param page_no
     * @param page_size
     * @return
     */
    @Override
    public DefaultRes<Iterable<Product>> findAll(final int page_no, final int page_size) {
        Iterable<Product> productIterable = productRepository.findAll();
        return DefaultRes.<Iterable<Product>>builder()
                .statusCode(StatusCode.OK)
                .responseMessage(ResponseMessage.READ)
                .responseData(productIterable)
                .build();
    }

    /**
     * 상품 세부 조회
     * @param product_idx
     * @return
     */
    @Override
    public DefaultRes<Product> findOne(final int product_idx) {
        final Optional<Product> product = productRepository.findById(product_idx);
        if (product.isPresent()) {
            return DefaultRes.<Product>builder().statusCode(StatusCode.OK).responseMessage(ResponseMessage.READ).responseData(product.get()).build();
        }
        return DefaultRes.<Product>builder().statusCode(StatusCode.NOT_FOUND).responseMessage(ResponseMessage.NOT_FOUND).build();
    }

    public DefaultRes<Product> test(PageRequest pageRequest) {
        return null;
    }
}
