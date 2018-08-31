package com.kakaoix.report.service;

import com.kakaoix.report.model.res.DefaultRes;
import com.kakaoix.report.model.req.PaymentDto;

/**
 * Created by ds on 2018-08-30.
 */

public interface PaymentService<Payment> {
    DefaultRes<Payment> findAll();
    DefaultRes<Payment> findOne(final int idx);
    DefaultRes<Payment> payment(final PaymentDto paymentDto);
}
