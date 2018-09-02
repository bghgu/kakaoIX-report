package com.kakaoix.report.repository;

import com.kakaoix.report.domain.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ds on 2018-08-30.
 */

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    /**
     * 자기 자신이 결제한 결제 목록 조회
     *
     * @param user_idx 결제 한 사람의 회원 고유 IDX
     * @param pageable 페이지네이션
     * @return 결제 목록
     */
    Page<Payment> findByUserIdx(final int user_idx, final Pageable pageable);
}
