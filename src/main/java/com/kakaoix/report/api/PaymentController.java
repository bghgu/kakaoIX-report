package com.kakaoix.report.api;

import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.utils.ResponseMessage;
import com.kakaoix.report.utils.StatusCode;
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

    @GetMapping("")
    public ResponseEntity<DefaultRes> getAllPayments(
            @RequestParam(value = "page_no", defaultValue = "1", required = false) final int page_no,
            @RequestParam(value = "page_size", defaultValue = "10", required = false) final int page_size
    ) {
        try {
            //return new ResponseEntity<DefaultRes<User>>(null, HttpStatus.OK);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<DefaultRes>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{payment_idx}")
    public ResponseEntity<DefaultRes> getPayment(@PathVariable final int payment_idx) {
        try {
            //return new ResponseEntity<DefaultRes<User>>(null, HttpStatus.OK);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<DefaultRes>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<DefaultRes> payment() {
        return null;
    }
}
