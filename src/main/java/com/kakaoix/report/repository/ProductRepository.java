package com.kakaoix.report.repository;

import com.kakaoix.report.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ds on 2018-08-31.
 */

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
