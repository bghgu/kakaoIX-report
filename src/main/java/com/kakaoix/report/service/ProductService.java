package com.kakaoix.report.service;

import com.kakaoix.report.domain.Product;
import com.kakaoix.report.model.res.DefaultRes;
import org.springframework.data.domain.PageRequest;

/**
 * Created by ds on 2018-08-29.
 */

public interface ProductService {
    DefaultRes<Product> findAll(final int page_no, final int page_size);

    DefaultRes<Product> test(final PageRequest pageRequest);
}
