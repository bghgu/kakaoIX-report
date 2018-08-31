package com.kakaoix.report.domain;

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
    private int payment_idx;

    private int user_idx;

    private int product_idx;

    private int quantity;

    private LocalDateTime payment_at;
}
