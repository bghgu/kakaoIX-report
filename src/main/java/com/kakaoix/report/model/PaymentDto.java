package com.kakaoix.report.model;

import lombok.Data;

/**
 * Created by ds on 2018-08-31.
 */

@Data
public class PaymentDto {

    private int user_idx;
    private int product_idx;
    private int quantity;

}
