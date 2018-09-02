package com.kakaoix.report.service.Impl;

import com.kakaoix.report.domain.Payment;
import com.kakaoix.report.domain.Product;
import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.model.Pagination;
import com.kakaoix.report.model.PaymentDto;
import com.kakaoix.report.repository.PaymentRepository;
import com.kakaoix.report.service.PaymentService;
import com.kakaoix.report.service.ProductService;
import com.kakaoix.report.utils.ResponseMessage;
import com.kakaoix.report.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by ds on 2018-08-31.
 */

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final ProductService productService;

    @Autowired
    public PaymentServiceImpl(final PaymentRepository paymentRepository, final ProductService productService) {
        this.paymentRepository = paymentRepository;
        this.productService = productService;
    }

    /**
     * 결제 내역 전체 조회
     *
     * @return
     */
    @Override
    public DefaultRes<Iterable<Payment>> findAll(final int userIdx, final Pagination pagination) {
        Iterable<Payment> paymentIterable = paymentRepository.findByUserIdx(userIdx, pagination);
        return DefaultRes.<Iterable<Payment>>builder()
                .statusCode(StatusCode.OK)
                .responseMessage(ResponseMessage.READ_PAYMENT_LIST)
                .responseData(paymentIterable)
                .build();
    }

    /**
     * 결제 상세 조회
     *
     * @param payment_idx
     * @return
     */
    @Override
    public DefaultRes<Payment> findOne(final int userIdx, final int payment_idx) {
        final Optional<Payment> payment = paymentRepository.findById(payment_idx);
        if (payment.isPresent()) {
            if (payment.get().getUserIdx() == userIdx) {
                return DefaultRes.<Payment>builder()
                        .statusCode(StatusCode.OK)
                        .responseMessage(ResponseMessage.READ_PAYMENT)
                        .responseData(payment.get())
                        .build();
            }
            return DefaultRes.<Payment>builder()
                    .statusCode(StatusCode.FORBIDDEN)
                    .responseMessage(ResponseMessage.READ_PAYMENT_FAIL)
                    .build();
        }
        return DefaultRes.<Payment>builder()
                .statusCode(StatusCode.NOT_FOUND)
                .responseMessage(ResponseMessage.NOT_FOUND_PAYMENT)
                .build();
    }

    /**
     * 결제
     *
     * @param paymentDto
     * @return
     */
    @Transactional
    @Override
    public DefaultRes<Payment> payment(final PaymentDto paymentDto) {
        final Optional<Product> product = productService.getProduct(paymentDto.getProductIdx());
        if (!product.isPresent()) {
            return DefaultRes.<Payment>builder()
                    .statusCode(StatusCode.BAD_REQUEST)
                    .responseMessage(ResponseMessage.NOT_FOUND_PRODUCT)
                    .build();
        }
        final Payment payment = new Payment(paymentDto);
        final double total_price = product.get().getPrice() * payment.getQuantity();
        payment.setTotal_price(total_price);
        try {
            paymentRepository.save(payment);
        } catch (Exception e) {
            return DefaultRes.<Payment>builder()
                    .statusCode(StatusCode.INTERNAL_SERVER_ERROR)
                    .responseMessage(ResponseMessage.PAYMENT_FAIL)
                    .build();
        }
        return DefaultRes.<Payment>builder()
                .statusCode(StatusCode.CREATED)
                .responseMessage(ResponseMessage.PAYMENT_SUCCESS)
                .build();
    }
}
