package com.kakaoix.report.model;

import lombok.Data;

/**
 * Created by ds on 2018-08-31.
 */

@Data
public class PaymentDto {
    private int userIdx;
    private int productIdx;
    private int quantity;
}
