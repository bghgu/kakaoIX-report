package com.kakaoix.report.service;

import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.model.PaymentDto;

/**
 * Created by ds on 2018-08-30.
 */

public interface PaymentService<Payment> {
    DefaultRes<Payment> findAll();

    DefaultRes<Payment> findOne(final int payment_idx);

    DefaultRes<Payment> payment(final PaymentDto paymentDto);
}
