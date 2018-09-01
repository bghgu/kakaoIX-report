package com.kakaoix.report.service.Impl;

import com.kakaoix.report.domain.Payment;
import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.model.PaymentDto;
import com.kakaoix.report.repository.PaymentRepository;
import com.kakaoix.report.service.PaymentService;
import com.kakaoix.report.utils.ResponseMessage;
import com.kakaoix.report.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * Created by ds on 2018-08-31.
 */
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(final PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    /**
     * 결제 내역 전체 조회
     * 페이지네이션 작업 안함
     * @return
     */
    @Override
    public DefaultRes<Iterable<Payment>> findAll() {
        final int user_idx = 1;
        Iterable<Payment> paymentIterable = null;
        return DefaultRes.<Iterable<Payment>>builder()
                .statusCode(StatusCode.OK)
                .responseMessage(ResponseMessage.READ)
                .responseData(paymentIterable)
                .build();
    }

    /**
     * 결제 상세 조회
     * @param payment_idx
     * @return
     */
    @Override
    public DefaultRes<Payment> findOne(final int payment_idx) {
        final Optional<Payment> payment = paymentRepository.findById(payment_idx);
        if (payment.isPresent()) {
            return DefaultRes.<Payment>builder()
                    .statusCode(StatusCode.OK)
                    .responseMessage(ResponseMessage.READ)
                    .responseData(payment.get())
                    .build();
        }
        return DefaultRes.<Payment>builder()
                .statusCode(StatusCode.NOT_FOUND)
                .responseMessage(ResponseMessage.NOT_FOUND)
                .build();
    }

    /**
     * 결제
     * @param paymentDto
     * @return
     */
    @Override
    public DefaultRes<Payment> payment(final PaymentDto paymentDto) {
        final Payment payment = Payment.builder()
                .userIdx(1)
                .productIdx(paymentDto.getProduct_idx())
                .quantity(paymentDto.getQuantity())
                .build();
        paymentRepository.save(payment);
        return DefaultRes.<Payment>builder()
                .statusCode(StatusCode.CREATED)
                .responseMessage(ResponseMessage.CREATED)
                .build();
    }
}
