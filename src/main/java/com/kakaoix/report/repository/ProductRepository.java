package com.kakaoix.report.repository;

import com.kakaoix.report.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by ds on 2018-08-31.
 */

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
