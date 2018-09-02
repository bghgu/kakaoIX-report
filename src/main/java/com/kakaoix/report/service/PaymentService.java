package com.kakaoix.report.service;

import com.kakaoix.report.domain.Payment;
import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.model.Pagination;
import com.kakaoix.report.model.PaymentDto;

/**
 * Created by ds on 2018-08-30.
 */

public interface PaymentService {
    DefaultRes<Iterable<Payment>> findAll(final int userIdx, final Pagination pagination);

    DefaultRes<Payment> findOne(final int userIdx, final int payment_idx);

    DefaultRes<Payment> payment(final PaymentDto paymentDto);
}
