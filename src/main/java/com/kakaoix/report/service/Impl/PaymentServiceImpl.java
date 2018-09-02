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

import java.util.List;
import java.util.Optional;

/**
 * Created by ds on 2018-08-31.
 */

/**
 * 결제 서비스 구현체
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final ProductService productService;

    /**
     * Repository 의존성 주입
     */
    @Autowired
    public PaymentServiceImpl(final PaymentRepository paymentRepository, final ProductService productService) {
        this.paymentRepository = paymentRepository;
        this.productService = productService;
    }

    /**
     * 회원이 결제한 결제 목록 조회
     *
     * @param userIdx    회원 고유 IDX
     * @param pagination 페이지네이션
     * @return 결제 목록
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
     * 결제 내역 조회
     * 결제 한 사람만 내역을 조회 가능
     *
     * @param userIdx     결제한 사람의 회원 고유 IDX
     * @param payment_idx 결제 고유 IDX
     * @return 결제 내역
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
     * 단일 품목 결제
     * 결제 실패 시 Rollback
     *
     * @param paymentDto 결제 내용
     * @return 결제 결과 메시지
     */
    @Transactional
    @Override
    public DefaultRes<Payment> payment(final PaymentDto paymentDto) {
        final Optional<Product> product = productService.getProduct(paymentDto.getProductIdx());
        if (!product.isPresent()) {
            return DefaultRes.<Payment>builder()
                    .statusCode(StatusCode.NOT_FOUND)
                    .responseMessage(ResponseMessage.NOT_FOUND_PRODUCT)
                    .build();
        }
        final Payment payment = new Payment(paymentDto);
        // 결제 대금
        final double total_price = product.get().getPrice() * payment.getQuantity();
        payment.setTotal_price(total_price);
        // 결제 시 오류 확인
        try {
            paymentRepository.save(payment);
        } catch (Exception e) {
            return DefaultRes.<Payment>builder()
                    .statusCode(StatusCode.BAD_REQUEST)
                    .responseMessage(ResponseMessage.PAYMENT_FAIL)
                    .build();
        }
        return DefaultRes.<Payment>builder()
                .statusCode(StatusCode.CREATED)
                .responseMessage(ResponseMessage.PAYMENT_SUCCESS)
                .build();
    }
}
