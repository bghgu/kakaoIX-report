package com.kakaoix.report.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kakaoix.report.model.PaymentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by ds on 2018-08-30.
 */

@Data
@Entity
@AllArgsConstructor
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentIdx;

    private int userIdx;

    private int productIdx;

    private int quantity;

    private double total_price;

    private LocalDateTime payment_at;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productIdx", insertable = false, updatable = false)
    private Product product;

    public Payment(final PaymentDto paymentDto) {
        this.userIdx = paymentDto.getUserIdx();
        this.productIdx = paymentDto.getProductIdx();
        this.quantity = paymentDto.getQuantity();
    }
}
