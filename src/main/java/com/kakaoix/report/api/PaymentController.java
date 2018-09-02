package com.kakaoix.report.api;

import com.kakaoix.report.domain.Payment;
import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.model.Pagination;
import com.kakaoix.report.model.PaymentDto;
import com.kakaoix.report.service.PaymentService;
import com.kakaoix.report.utils.ResponseMessage;
import com.kakaoix.report.utils.StatusCode;
import com.kakaoix.report.utils.auth.Auth;
import com.kakaoix.report.utils.auth.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ds on 2018-08-31.
 */

@RestController
@RequestMapping("payments")
public class PaymentController {

    private static final DefaultRes FAIL_DEFAULT_RES = new DefaultRes(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(final PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("")
    @Auth
    public ResponseEntity<DefaultRes<Iterable<Payment>>> getAllPayments(
            @RequestHeader("Authorization") final String jwt,
            @RequestParam(value = "offset", defaultValue = "0", required = false) final int offset,
            @RequestParam(value = "limit", defaultValue = "10", required = false) final int limit,
            @RequestParam(value = "sort", defaultValue = "paymentIdx", required = false) final String sort
    ) {
        try {
            final Pagination pagination = Pagination.builder().offset(offset).limit(limit).sort(new Sort(Sort.Direction.DESC, sort)).build();
            return new ResponseEntity<DefaultRes<Iterable<Payment>>>(paymentService.findAll(Jwt.decode(jwt).getUser_idx(), pagination), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<DefaultRes<Iterable<Payment>>>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{payment_idx}")
    @Auth
    public ResponseEntity<DefaultRes<Payment>> getPayment(
            @RequestHeader("Authorization") final String jwt,
            @PathVariable final int payment_idx) {
        try {
            return new ResponseEntity<DefaultRes<Payment>>(paymentService.findOne(Jwt.decode(jwt).getUser_idx(), payment_idx), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<DefaultRes<Payment>>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    @Auth
    public ResponseEntity<DefaultRes> payment(
            @RequestHeader("Authorization") final String jwt,
            @RequestBody PaymentDto paymentDto
    ) {
        try {
            paymentDto.setUserIdx(Jwt.decode(jwt).getUser_idx());
            return new ResponseEntity<DefaultRes>(paymentService.payment(paymentDto), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<DefaultRes>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
