package com.kakaoix.report.repository;

import com.kakaoix.report.domain.Payment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ds on 2018-08-30.
 */
public interface PaymentRepository extends CrudRepository<Payment, Integer> {

}
