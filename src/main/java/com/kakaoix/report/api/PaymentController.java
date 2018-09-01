package com.kakaoix.report.api;

import com.kakaoix.report.domain.Payment;
import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.model.PaymentDto;
import com.kakaoix.report.service.PaymentService;
import com.kakaoix.report.utils.ResponseMessage;
import com.kakaoix.report.utils.StatusCode;
import com.kakaoix.report.utils.auth.Auth;
import org.springframework.beans.factory.annotation.Autowired;
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
            @RequestParam(value = "page_no", defaultValue = "1", required = false) final int page_no,
            @RequestParam(value = "page_size", defaultValue = "10", required = false) final int page_size
    ) {
        try {
            return new ResponseEntity<DefaultRes<Iterable<Payment>>>(paymentService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<DefaultRes<Iterable<Payment>>>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{payment_idx}")
    @Auth
    public ResponseEntity<DefaultRes<Payment>> getPayment(@PathVariable final int payment_idx) {
        try {
            return new ResponseEntity<DefaultRes<Payment>>(paymentService.findOne(payment_idx), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<DefaultRes<Payment>>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    @Auth
    public ResponseEntity<DefaultRes> payment(@RequestBody final PaymentDto paymentDto) {
        try {
            return new ResponseEntity<DefaultRes>(paymentService.payment(paymentDto), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<DefaultRes>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
