package com.kakaoix.report.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by ds on 2018-08-30.
 */

@Data
@Entity
@Builder
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentIdx;

    private int userIdx;

    private int productIdx;

    private int quantity;

    private LocalDateTime payment_at;

    @OneToOne(mappedBy = "payment", fetch = FetchType.EAGER)
    private Product product;
}
