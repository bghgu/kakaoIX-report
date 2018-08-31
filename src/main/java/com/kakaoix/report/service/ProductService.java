package com.kakaoix.report.service;

import com.kakaoix.report.domain.Product;
import com.kakaoix.report.model.DefaultRes;

/**
 * Created by ds on 2018-08-29.
 */

public interface ProductService {
    DefaultRes<Iterable<Product>> findAll(final int page_no, final int page_size);

    DefaultRes<Product> findOne(final int product_idx);
}
