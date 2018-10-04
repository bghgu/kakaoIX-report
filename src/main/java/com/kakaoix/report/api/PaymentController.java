package com.kakaoix.report.api;

import com.kakaoix.report.domain.Payment;
import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.model.Pagination;
import com.kakaoix.report.model.PaymentDto;
import com.kakaoix.report.service.PaymentService;
import com.kakaoix.report.utils.ResponseMessage;
import com.kakaoix.report.utils.StatusCode;
import com.kakaoix.report.utils.auth.Auth;
import com.kakaoix.report.service.JwtService;
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

    /**
     * 실패 시 기본 반환 Response
     */
    private static final DefaultRes FAIL_DEFAULT_RES = new DefaultRes(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);

    private final PaymentService paymentService;
    private final JwtService jwtService;

    /**
     * 서비스 의존성 주입
     */
    public PaymentController(final PaymentService paymentService, final JwtService jwtService) {
        this.paymentService = paymentService;
        this.jwtService = jwtService;
    }

    /**
     * 현재 로그인한 사용자의 결제 목록 조회
     *
     * @param jwt    토큰값
     * @param offset 목록 시작, 기본값 0
     * @param limit  조회할 결제 갯수, 기본값 10
     * @param sort   정렬 기준, 기본값 결제 고유 IDX
     * @param order  정렬 방법, 기본값 내림차순
     * @return 결제 목록
     */
    @GetMapping("")
    @Auth
    public ResponseEntity<DefaultRes<Iterable<Payment>>> getAllPayments(
            @RequestHeader("Authorization") final String jwt,
            @RequestParam(value = "offset", defaultValue = "0", required = false) final int offset,
            @RequestParam(value = "limit", defaultValue = "10", required = false) final int limit,
            @RequestParam(value = "sort", defaultValue = "paymentIdx", required = false) final String sort,
            @RequestParam(value = "order", defaultValue = "desc", required = false) final String order
    ) {
        try {
            Pagination pagination;
            if (order.equals("asc")) {
                pagination = Pagination.builder()
                        .offset(offset)
                        .limit(limit)
                        .sort(new Sort(Sort.Direction.ASC, sort))
                        .build();
            } else {
                pagination = Pagination.builder()
                        .offset(offset)
                        .limit(limit)
                        .sort(new Sort(Sort.Direction.DESC, sort))
                        .build();
            }
            return new ResponseEntity<>(paymentService.findAll(jwtService.decode(jwt).getUser_idx(), pagination), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<DefaultRes<Iterable<Payment>>>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 결제 내역 조회
     * 자기 자신의 결제 내역만 조회 가능
     *
     * @param jwt         토큰값
     * @param payment_idx 결제 고유 IDX
     * @return 결제 내역
     */
    @GetMapping("/{payment_idx}")
    @Auth
    public ResponseEntity<DefaultRes<Payment>> getPayment(
            @RequestHeader("Authorization") final String jwt,
            @PathVariable final int payment_idx) {
        try {
            return new ResponseEntity<>(paymentService.findOne(jwtService.decode(jwt).getUser_idx(), payment_idx), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<DefaultRes<Payment>>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 결제
     * 단일 품목 결제
     *
     * @param jwt        토큰값
     * @param paymentDto 결제 폼
     * @return 결제 결과 메시지
     */
    @PostMapping("")
    @Auth
    public ResponseEntity<DefaultRes> payment(
            @RequestHeader("Authorization") final String jwt,
            @RequestBody PaymentDto paymentDto
    ) {
        try {
            paymentDto.setUserIdx(jwtService.decode(jwt).getUser_idx());
            return new ResponseEntity<>(paymentService.payment(paymentDto), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
