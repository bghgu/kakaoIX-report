package com.kakaoix.report.service;

import com.kakaoix.report.domain.Product;
import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.model.Pagination;

import java.util.Optional;

/**
 * Created by ds on 2018-08-29.
 */

/**
 * 상품 서비스 인터페이스
 */
public interface ProductService {
    DefaultRes<Iterable<Product>> findAll(final Pagination pagination);

    DefaultRes<Product> findOne(final int productIdx);

    Optional<Product> getProduct(final int productIdx);
}
