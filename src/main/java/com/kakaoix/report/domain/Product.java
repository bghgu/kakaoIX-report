package com.kakaoix.report.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ds on 2018-08-29.
 */

/**
 * 상품 도메인
 */
@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productIdx;

    private String name;

    private int price;

    private String intro;

    private String image;

    /**
     * 결제 도메인과 join
     */
    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Payment> payment;
}
