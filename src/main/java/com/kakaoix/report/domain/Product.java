package com.kakaoix.report.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by ds on 2018-08-29.
 */

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_idx;

    private String name;

    private int price;

    private String intro;

    private String image;
}
