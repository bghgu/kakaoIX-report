package com.kakaoix.report.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by ds on 2018-08-29.
 */

@Data
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int item_idx;

    private String name;

    private int price;

    private String img;

    private String intro;
}
